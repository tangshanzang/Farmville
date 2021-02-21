package Farmville.Food;

public class Millet extends Food{

    public Millet(int kg){
        setFoodPrice(Food.Price.MILLET.foodPrice);
        this.kg = kg;
    }
}
