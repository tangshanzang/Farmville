package Farmville.Animal;

public class Chicken extends Animal{

    public Chicken(){
        setPrice(Price.CHICKEN.animalPrice);
        maxOffSpring = 10;
        offSpringLeft = 10;
    }

}
