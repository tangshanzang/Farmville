package Farmville.Animal;

public class Cow extends Animal{

    public Cow(){
        setPrice(Price.COW.animalPrice);
        maxOffSpring = 3;
        offSpringLeft = 3;
    }
}
