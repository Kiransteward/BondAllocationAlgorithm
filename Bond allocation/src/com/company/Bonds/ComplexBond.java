package com.company.Bonds;

import com.company.Bond;
import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;

import java.util.List;

public class ComplexBond extends Bond {
    List<Double> risk;
    public ComplexBond(double price, double quantity, AssetClass assetClass, Rating rating, List<Double> risk) {
        super(price, quantity, assetClass, rating);
        this.risk = risk;
    }
}