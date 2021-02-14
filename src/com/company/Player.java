package com.company;

import java.util.ArrayList;

public class Player {
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
    }

    public int getMoney(){ return this.money;}

    public void setMoney(int money){ this.money = money;}

    public String getName(){return this.name;}

    public ArrayList<Animal> getOwnedAnimal(){return this.ownedAnimal;}

    public ArrayList<Food> getOwnedFood(){return this.ownedFood;}

    public boolean isPlayerRemainingInGame() {return isPlayerRemainingInGame;}
}
