package com.company;

import java.util.Scanner;

public class Store {

    public Store(){

    }

    private Animal animal = new Animal() {
    };
    private Game game;
    int userInput1;

    Scanner myScanner = new Scanner(System.in);
    public void setGame(Game game) {
        this.game = game;
    }


    public boolean enoughMoney(Animal animal, Player player) {
        return player.getMoney() >= animal.getPrice();
    }

    public void BuyNewAnimals(){
        userInput1 = Dialog.menu("Store","Buy Chicken / 50 Gold","Buy Cow / 250 Gold","Buy Horse / 250 Gold","Buy Pig / 150 Gold","Buy Sheep / 150 Gold");
        switch (userInput1){
            case 1 -> { }
            case 2 -> { }
            case 3 -> { }
            case 4 -> { }
            case 5 -> { }
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
