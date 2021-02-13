package com.company;

public abstract class Animal {
    String name, gender;
    int health, price;
    int startingHealth = 100;

    public Animal(){

    }

    public int getPrice(){return this.price;}

    public int getHealth(){return this.health;}

    public String getName(){return this.name;}

    public String getType() {
        return this.getClass().getSimpleName();
    }
}

