package Farmville.Animal;

public class Sheep extends Animal{

    public Sheep(){
        setPrice(Price.SHEEP.animalPrice);
        maxOffSpring = 6;
        offSpringLeft = 6;
    }
}
