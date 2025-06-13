package com.company;

import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;

import java.util.HashMap;
import java.util.UUID;

public abstract class Deal {
    protected UUID id;
    protected double price;
    protected HashMap<AssetClass, Double> minAssetRequirements;
    protected HashMap<Rating,Double> minCreditRatings;

    public Deal(double price, HashMap<AssetClass, Double> minAssetRequirements, HashMap<Rating, Double> minCreditRatings) {
        this.id = UUID.randomUUID();
        this.price = price;
        this.minAssetRequirements = minAssetRequirements;
        this.minCreditRatings = minCreditRatings;
    }

    public HashMap<AssetClass, Double> getMinAssetRequirements() {
        return minAssetRequirements;
    }

    public HashMap<Rating, Double> getMinCreditRatings() {
        return minCreditRatings;
    }

}
