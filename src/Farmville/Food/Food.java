package Farmville.Food;

import java.io.Serializable;

public abstract class Food implements Serializable {
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

    public void setFoodPrice(int price){ this.price = price;}

}
