package com.company;

public class Pig extends Animal{

    public Pig(){
        setPrice(Price.PIG.animalPrice);
    }

    public static boolean checkIfCanEat(Player player, Food food){
        canEat = food.getClass().equals(player.ownedFood.get(0).getClass());
        return canEat;

    }
}
