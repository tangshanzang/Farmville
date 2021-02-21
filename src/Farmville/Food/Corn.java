package Farmville.Food;

public class Corn extends Food{

    public Corn(int kg){
        setFoodPrice(Food.Price.CORN.foodPrice);
        this.kg = kg;
    }
}
