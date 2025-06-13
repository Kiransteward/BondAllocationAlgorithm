package com.company.Bonds;

import com.company.DataTypes.AssetClass;
import com.company.Bond;
import com.company.DataTypes.Rating;

import java.util.List;

public class BasicBond extends Bond {

    public BasicBond(double price, double quantity, AssetClass assetClass, Rating rating) {
        super(price, quantity, assetClass, rating);
    }

}

