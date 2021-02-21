package Farmville;

import Farmville.Animal.Animal;
import Farmville.Food.Corn;
import Farmville.Food.Food;
import Farmville.Food.Grass;
import Farmville.Food.Millet;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    String name;
    int money;
    ArrayList<Animal> ownedAnimal;
    ArrayList<Food> ownedFood;
    boolean isPlayerRemainingInGame = true;

    public Player(String name, int money){
        this.name = name;
        this.money = money;
        this.ownedAnimal = new ArrayList<>();
        this.ownedFood = new ArrayList<>();
        ownedFood.add(new Corn(0));
        ownedFood.add(new Grass(0));
        ownedFood.add(new Millet(0));
    }

    public int getMoney(){ return this.money;}

    public void setMoney(int money){ this.money = money;}

    public String getName(){return this.name;}

}
