//package com.company;
//
//import java.util.*;
//
//public class GreedyAllocator implements Allocator{
//    List<BasicBond> bonds;
//
//    public GreedyAllocator(List<BasicDeal> deals, List<BasicBond> bonds) {
//        super(deals, bonds);
//    }
//
////    @Override
////    public double allocate() {
////        int n = deals.size();
////        for(Bond bond: bonds){
////            int index = 0;
////            while(index<n && bond.value>0){
////                if(deals.get(index).price > 0){
////                    Deal currentDeal = deals.get(index);
////                    HashMap<AssetClass,Integer> minAssetClassReq = currentDeal.minAssetRequirements;
////                    HashMap<Rating,Integer> minCreditRating = currentDeal.minCreditRatings;
////                    int quantityOfAvailableAssetClass = minAssetClassReq.getOrDefault(bond.assetClass, 0);
////                    int quantityOfAvailableRating = minCreditRating.getOrDefault(bond.rating,0);
////                    if(quantityOfAvailableAssetClass > 0 || quantityOfAvailableRating > 0){
////                        int amountToAllocate = (int) Math.min(Math.min(bond.value,currentDeal.price), Math.max(quantityOfAvailableAssetClass, quantityOfAvailableRating));
////                        currentDeal.minAssetRequirements.replace(bond.assetClass, quantityOfAvailableAssetClass-amountToAllocate);
////                        currentDeal.minCreditRatings.replace(bond.rating, quantityOfAvailableRating-amountToAllocate);
////                        bond.value -= amountToAllocate;
////                        currentDeal.price -= amountToAllocate;
////                    }
////
////                }
////                index ++;
////            }
////        }
////        for(Deal deal: deals){
////            if(!(deal.minCreditRatings.values().stream().filter(x->x>0).findFirst().isPresent() || deal.minAssetRequirements.values().stream().filter(x->x>0).findFirst().isPresent()) && deal.price>0){
////                double remaining = deal.price;
////                Optional<Bond> optionalBond = bonds.stream().max(Comparator.comparing(x->x.price-remaining));
////                Bond bond =  optionalBond.get();
////                double amountToClear = Math.min(remaining,bond.price);
////                bond.price -= amountToClear;
////                deal.price -= amountToClear;
////            }
////        }
////        return computeLoan(deals);
////    }
//    public double allocate() {
//        Map<Deal, Map<Bond, Double>> allocations = new HashMap<>();
//        int n = deals.size();
//
//        // Phase 1: Try to fulfill min asset class and credit rating requirements greedily
//        for (BasicBond bond : bonds) {
//            int index = 0;
//
//            while (index < n && bond.value > 0) {
//                BasicDeal currentDeal = deals.get(index);
//
//                if (currentDeal.price > 0) {
//                    HashMap<AssetClass, Double> minAssetClassReq = currentDeal.minAssetRequirements;
//                    HashMap<Rating, Double> minCreditRating = currentDeal.minCreditRatings;
//
//                    double assetReq = minAssetClassReq.getOrDefault(bond.assetClass, 0.0);
//                    double ratingReq = minCreditRating.getOrDefault(bond.rating, 0.0);
//
//                    // Determine max amount that can be allocated
//                    double allocatable = Math.min(Math.min(bond.value,currentDeal.price), Math.max(assetReq, ratingReq));
//
//                    if (allocatable > 0) {
//
//                        // Update deal requirements
//                        if (minAssetClassReq.containsKey(bond.assetClass)) {
//                            minAssetClassReq.put(bond.assetClass, Math.max(assetReq - allocatable,0));
//                        }
//                        if (minCreditRating.containsKey(bond.rating)) {
//                            minCreditRating.put(bond.rating, Math.max(ratingReq - allocatable,0));
//                        }
//
//                        // Update deal and bond values
//                        bond.value -= allocatable;
//                        bond.quantity = bond.value/bond.price;
//                        currentDeal.price -= allocatable;
//                        System.out.println(allocatable/bond.price + " units of bond: " + bond.toString() + " was allocated to deal: " + currentDeal.toString());
//                    }
//                    allocations
//                            .computeIfAbsent(currentDeal, k -> new HashMap<>())
//                            .merge(bond, allocatable, Double::sum);
//                }
//
//                index++;
//            }
//        }
//
//        // Phase 2: Try to fulfill remaining deal price without asset/rating constraints
//        allocations = assignUnconstrained(allocations);
//
//        // Phase 3: Compute and return remaining loan requirement
//        return computeLoan();
//    }
//
//}
