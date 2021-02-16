package com.company;

public class Cow extends Animal{

    public Cow(){
        setPrice(Price.COW.animalPrice);
    }

    public static boolean checkIfCanEat(Player player, Food food) {
        canEat = food.getClass().equals(player.ownedFood.get(1).getClass());
        return canEat;
    }
}
