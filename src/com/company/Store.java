package com.company;

import java.util.Scanner;

public class Store {



    public Store(){

    }


    private Game game;
    int userInput1;

    Scanner myScanner = new Scanner(System.in);
    public void setGame(Game game) {
        this.game = game;
    }


    public boolean enoughMoney(int cost, Player player) {
        return player.getMoney() >= cost;
    }

    public void BuyNewAnimalsMenu(Player player){
        userInput1 = Dialog.menu("Store","Buy Chicken / 50 Gold","Buy Cow / 250 Gold","Buy Horse / 250 Gold","Buy Pig / 150 Gold","Buy Sheep / 150 Gold");
        switch (userInput1){
            case 1 -> {
                BuyNewAnimals(player, Animal.Price.CHICKEN.animalPrice);
                //ADD ANIMALS!
            }
            case 2 -> {
                BuyNewAnimals(player, Animal.Price.COW.animalPrice);
            }
            case 3 -> {
                BuyNewAnimals(player, Animal.Price.HORSE.animalPrice);
            }
            case 4 -> {
                BuyNewAnimals(player, Animal.Price.PIG.animalPrice);
            }
            case 5 -> {
                BuyNewAnimals(player, Animal.Price.SHEEP.animalPrice);
            }
        }
    }

    public void BuyNewAnimals(Player player, int price){
        System.out.println("Your gold: " + player.getMoney());
        int totalCost = Dialog.totalCost(price, Dialog.askForQuantity());
        System.out.println("");
        if(enoughMoney(totalCost,player)){
            player.setMoney(player.getMoney() - totalCost);
            System.out.println("Trade has been done");
            System.out.println("Your gold: " + player.getMoney());
        }
        else{
            System.out.println("You don't have enough money!");
        }
    }

    public void BuyNewFood(){

    }

    public void FeedMyAnimals(){

    }

    public void MateMyAnimals(){

    }

    public void SellMyAnimals(){

    }
}
