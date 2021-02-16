package com.company;

public class Sheep extends Animal{

    public Sheep(){
        setPrice(Price.SHEEP.animalPrice);
    }

    public static boolean checkIfCanEat(Player player, Food food){
        canEat = food.getClass().equals(player.ownedFood.get(1).getClass());
        return canEat;
    }
}
