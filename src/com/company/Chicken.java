package com.company;

public class Chicken extends Animal{

    public Chicken(String name, String gender, int health){
        this.name = name;
        this.gender = gender;
        this.health = health;
        setPrice(Price.CHICKEN.animalPrice);
    }
}
