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
                BuyNewAnimals(player, Animal.Price.CHICKEN.animalPrice, userInput1);
                //ADD ANIMALS!--
                //Done, add animals to player has been added as method to BuyNewAnimals
            }
            case 2 -> {
                BuyNewAnimals(player, Animal.Price.COW.animalPrice, userInput1);
            }
            case 3 -> {
                BuyNewAnimals(player, Animal.Price.HORSE.animalPrice, userInput1);
            }
            case 4 -> {
                BuyNewAnimals(player, Animal.Price.PIG.animalPrice, userInput1);
            }
            case 5 -> {
                BuyNewAnimals(player, Animal.Price.SHEEP.animalPrice, userInput1);
            }
        }
    }

    public void BuyNewAnimals(Player player, int price, int userInput1){
        System.out.println("Your gold: " + player.getMoney());
        int quantity = Dialog.askForQuantity();
        int totalCost = Dialog.totalCost(price, quantity);
        System.out.println("");
        if(enoughMoney(totalCost,player)){
            for(int i = 0; i < quantity; i++){
                player.setMoney(player.getMoney() - price);
                //RandomName, Select Gender needs to be added --
                //Done, Added to method addAnimalToPlayer.
                switch(userInput1){
                    case 1 -> {addAnimalToPlayer(player, new Chicken());}
                    case 2 -> {addAnimalToPlayer(player, new Cow());}
                    case 3 -> {addAnimalToPlayer(player, new Horse());}
                    case 4 -> {addAnimalToPlayer(player, new Pig());}
                    case 5 -> {addAnimalToPlayer(player, new Sheep());}
                }
            }
            System.out.println("Trade has been done\n");
            System.out.println("Your gold: " + player.getMoney());
            for(int i = 0; i < player.ownedAnimal.size(); i++){
                System.out.println("Name: " + player.ownedAnimal.get(i).getName());
                System.out.println("Health: " + player.ownedAnimal.get(i).getHealth());
                System.out.println("Gender: " + player.ownedAnimal.get(i).getGender());
            }
        }
        else{
            System.out.println("You don't have enough money!");
        }
    }

    public void addAnimalToPlayer(Player player, Animal animal){

        Dialog.selectGender(animal);//GENDER METHOD NEEDED! --
        //Done, selectGender method let user random or decide the gender of bought animals

        Dialog.enterOrRandomName(animal);//ask for selectedName or randomName
        animal.setHealth(animal.startingHealth);//health set to startingHealth(100)
        player.ownedAnimal.add(animal);//add animal to player's animal list

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
