package com.company;

import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;

import java.util.UUID;

public abstract class Bond {
    protected UUID id;
    protected double price;
    protected double quantity;
    protected double value;
    protected AssetClass assetClass;
    protected Rating rating;

    public Bond(double price, double quantity, AssetClass assetClass, Rating rating) {
        this.price = price;
        this.quantity = quantity;
        this.value = price*quantity;
        this.assetClass = assetClass;
        this.rating = rating;
        this.id = UUID.randomUUID();
    }
}
