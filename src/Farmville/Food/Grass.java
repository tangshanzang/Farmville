package Farmville.Food;

public class Grass extends Food{

    public Grass(int kg){
        setFoodPrice(Food.Price.GRASS.foodPrice);
        this.kg = kg;
    }
}
