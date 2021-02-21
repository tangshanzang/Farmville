package Farmville.Animal;

public class Horse extends Animal{

    public Horse(){
        setPrice(Price.HORSE.animalPrice);
        maxOffSpring = 3;
        offSpringLeft = 3;
    }
}
