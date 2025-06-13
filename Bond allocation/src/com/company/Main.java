package com.company;

import com.company.Bonds.BasicBond;
import com.company.Bonds.ComplexBond;
import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;
import com.company.Deals.BasicDeal;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<AssetClass, Double> deal1Asset = new HashMap();
        deal1Asset.put(AssetClass.CORPORATE, 300.0);
        HashMap<Rating, Double> deal1Rating = new HashMap();
        deal1Rating.put(Rating.AAA,500.0);
        BasicDeal deal1 = new BasicDeal(1000, deal1Asset, deal1Rating);


        HashMap<AssetClass, Double> deal2Asset = new HashMap();
        HashMap<Rating, Double> deal2Rating = new HashMap();
        deal2Rating.put(Rating.AA,500.0);
        deal2Asset.put(AssetClass.CORPORATE,400.0);
        BasicDeal deal2 = new BasicDeal(900, deal2Asset, deal2Rating);


        BasicBond bond1 = new BasicBond(100, 10, AssetClass.CORPORATE, Rating.AAA);
        BasicBond bond2 = new BasicBond(100, 1, AssetClass.SOVERIGN, Rating.BB);
        BasicBond bond3 = new BasicBond(150, 4, AssetClass.MUNICIPLE, Rating.A);
        BasicBond bond4 = new BasicBond(100, 8, AssetClass.CORPORATE, Rating.AAA);

        GreedyAllocator greedyAllocator = new GreedyAllocator(Arrays.asList(deal1, deal2), Arrays.asList(bond4, bond3)); //Allocator works with multiple different type of deals and bonds.
        System.out.println(greedyAllocator.allocate());


    }
}
