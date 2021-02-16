package com.company;

public class Chicken extends Animal{

    public Chicken(){
        setPrice(Price.CHICKEN.animalPrice);
    }

    public static boolean checkIfCanEat(Player player, Food food){
        canEat = food.getClass().equals(player.ownedFood.get(2).getClass());
        return canEat;
    }
}
