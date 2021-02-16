package com.company;

public class Horse extends Animal{

    public Horse(){
        setPrice(Price.HORSE.animalPrice);
    }

    public static boolean checkIfCanEat(Player player, Food food){
        canEat = food.getClass().equals(player.ownedFood.get(1).getClass());
        return canEat;
    }
}
