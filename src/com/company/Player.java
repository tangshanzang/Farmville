package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    String name;
    int money;
    int kg;
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

    public void setFoodAmount(int kg){ this.kg = kg;}

    public String getName(){return this.name;}

    public ArrayList<Animal> getOwnedAnimal(){return this.ownedAnimal;}

    public ArrayList<Food> getOwnedFood(){return this.ownedFood;}

    public boolean isPlayerRemainingInGame() {return isPlayerRemainingInGame;}

}
