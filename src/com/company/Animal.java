package com.company;

public abstract class Animal {
    String name, gender;
    int health, price;
    int startingHealth = 100;

    public Animal(){

    }

    public enum Price{
        CHICKEN(50),
        COW(250),
        HORSE(250),
        PIG(150),
        SHEEP(150);
        public int animalPrice;
        Price(int animalPrice){ this.animalPrice = animalPrice;}
    }

    public int getPrice(){return this.price;}

    public void setPrice(int price){this.price = price;}

    public int getHealth(){return this.health;}

    public void setHealth(int health){ this.health = health;}

    public String getName(){return this.name;}

    public void setName(String name){ this.name = name;}

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String getGender(){return this.gender;}

    public void setGender(String gender){this.gender = gender;}
}

