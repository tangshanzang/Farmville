package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Food {
    int kg;
    int price;

    public enum Price{
        CORN(15),
        GRASS(20),
        MILLET(10);
        public int foodPrice;
        Price(int foodPrice){ this.foodPrice = foodPrice;}
    }

    public int getFoodAmount(){ return this.kg;}

    public void setFoodAmount(int kg){ this.kg = kg;}

    public int getFoodPrice(){ return this.price;}

    public void setFoodPrice(int price){ this.price = price;}
}
