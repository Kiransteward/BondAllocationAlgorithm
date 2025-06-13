package com.company.Deals;

import com.company.DataTypes.AssetClass;
import com.company.Deal;
import com.company.DataTypes.Rating;

import java.util.HashMap;


public class BasicDeal extends Deal {

    public BasicDeal(double price, HashMap<AssetClass, Double> minAssetRequirements, HashMap<Rating,Double> minCreditRatings) {
        super(price, minAssetRequirements, minCreditRatings);
    }
}
