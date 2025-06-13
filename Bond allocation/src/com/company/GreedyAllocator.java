package com.company;

import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;

import java.util.*;
import java.util.stream.Collectors;


public class GreedyAllocator implements Allocator{
    protected List<Deal> deals; // deals can contain any type of Deal
    protected List<Bond> bonds; // bonds can contain any type of Bond
    public GreedyAllocator(List<Deal> deals, List<Bond> bonds) {
        this.deals = deals;
        this.bonds = bonds;
    }

    @Override
    public double computeLoan() {
        return deals.stream().map(deal -> deal.price).reduce(Double::sum).get();
    }

    @Override
    public double allocate() {
        Map<Deal, Map<Bond, Double>> allocations = new HashMap<>();
        for(Bond bond: bonds){
            AssetClass assetClass = bond.assetClass;
            Rating rating = bond.rating;
            // get the deal which has the largest requirement for the bonds asset class
            List<Deal> sortedDeals = deals.stream()
                    .filter(deal -> deal.price > 0)
                    .filter(deal -> deal.minCreditRatings.getOrDefault(rating,0.0)>0 || deal.minAssetRequirements.getOrDefault(assetClass,0.0)>0)
//                    .sorted(Comparator.comparing(deal -> deal.minAssetRequirements.getOrDefault(assetClass, 0.0))) // get the deal which has the largest requirement for the bonds asset class
//                    .sorted(Comparator.comparing(deal -> -deal.price)) //prioritise the deal with the largest requirement for that bond
//                    .sorted(Comparator.comparing(deal -> -Math.max(deal.minAssetRequirements.getOrDefault(assetClass, 0.0), deal.minCreditRatings.getOrDefault(rating,0.0)))) // prioritise the deal with the most need for the bonds asset class or risk.
                    .sorted(Comparator.comparing(deal -> -(deal.minCreditRatings.getOrDefault(assetClass,0.0)+deal.minCreditRatings.getOrDefault(rating,0.0)/deal.price))) // prioritise deal where the bond makes the largest proportional impact.
                    .collect(Collectors.toList());
            for(Deal deal: sortedDeals){
                if(bond.price == 0) break;
                double availableAssetRequirement = deal.minAssetRequirements.getOrDefault(assetClass,0.0);
                double availableRiskRequirement = deal.minCreditRatings.getOrDefault(rating,0.0);
                //If the bond does not full fill any of the constrained risk/assetClass requirements then we dont allocate it and save it for the unconstrained allocation
                //e.g if Math.max(availableAssetRequirement,availableRiskRequirement) = 0 (no constraints fullfilled by this bond), then we skip the bond.
                double allocated = Math.min(Math.min(bond.value,deal.price), Math.max(availableAssetRequirement,availableRiskRequirement));
                deal.price -= allocated;
                deal.minAssetRequirements.replace(assetClass, Math.max(availableAssetRequirement-allocated,0));
                deal.minCreditRatings.replace(rating, Math.max(availableRiskRequirement-allocated,0));
                bond.value -= allocated;
                if(bond.value<0) throw new IllegalArgumentException("Bond value cannot be negative: " + bond.value);
                bond.quantity = bond.price == 0 ? 0 : bond.value/bond.price;
                allocations
                        .computeIfAbsent(deal, k -> new HashMap<>())
                        .merge(bond, allocated, Double::sum);
            }

        }
        allocations = assignUnconstrained(allocations);
        return computeLoan();
    }

    @Override
    public Map<Deal, Map<Bond, Double>> assignUnconstrained(Map<Deal, Map<Bond, Double>> allocations) {
        for(Deal deal : deals){
            if(deal.price<0) throw new IllegalArgumentException("Deal price cannot be negative: " + deal.price);
            double amountLeft = deal.price - deal.minCreditRatings.values().stream().reduce(Double::sum).get() - deal.minAssetRequirements.values().stream().reduce(Double::sum).get();
            Optional<Bond> optionalBond = bonds.stream().max(Comparator.comparing(x->x.value)); //pick the bond closest in absolute value to the value of the deal to avoid overallocation.
            if(optionalBond.isPresent() && amountLeft>0){ //If there is available bonds and unconstrained value in deal, then assign.
                Bond bond = optionalBond.get();
                double assignable = Math.min(amountLeft,bond.value);
                bond.price-=assignable;
                bond.quantity = bond.price/bond.value;
                deal.price -= assignable;
                allocations
                        .computeIfAbsent(deal, x-> new HashMap<>())
                        .merge(bond, assignable, Double::sum);
            }
        }
        return allocations;
    }

}
