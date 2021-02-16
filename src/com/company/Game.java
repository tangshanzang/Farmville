package com.company;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    String name;
    int totalRounds, totalPlayers, userInput1, startingFund;
    int round = 1;
    int currentRound = 1;
    int activePlayer = 0;
    ArrayList<Player> players = new ArrayList<>();
    public Store store = new Store();

    public Game(){
        GameSetting();
    }

    public void GameSetting(){
        System.out.println("-".repeat(50));
        System.out.println("Welcome to FarmVille!");
        System.out.println("-".repeat(50));
        System.out.println("In this game, you and your friends will compete with each other by managing a farm. ");
        System.out.println("You can buy, sell, feed, mate your animals to generate profits.");
        System.out.println("As you progress, your animals can get sick and die if they are not taken good cared off, so be careful with that!");
        System.out.println("When the game ends, all your animals will be turned into gold, the player with the most gold wins the game.");
        System.out.println("We will now let you modify the game setting, good luck, my friends!");
        TotalRounds();
    }

    public void TotalRounds(){
        System.out.println("-".repeat(50));
        totalRounds = Dialog.promptInt("How many rounds do you want to play in total?(5-30)","Please enter a number between 5 - 30",
                "Numbers only!",5,30);
        System.out.println("Total rounds has been set to: " + totalRounds);
        TotalPlayers();
    }

    public void TotalPlayers(){
        System.out.println("-".repeat(50));
        totalPlayers = Dialog.promptInt("How many players will there be?(1-4)","Please enter a number between 1 - 4",
                "Numbers only!",1,4);
        System.out.println(totalPlayers + " players will be participating the game.");
        SettingStartingFund();
    }


    public void SettingStartingFund(){
        System.out.println("-".repeat(50));
        System.out.println("Please select starting fund:");
        System.out.println("1. 500 Golds");
        System.out.println("2. 1000 Golds");
        System.out.println("3. 1500 Golds");
        Scanner myScanner = new Scanner(System.in);
        userInput1 = myScanner.nextInt();
        myScanner.nextLine();
        switch(userInput1){
            case 1 -> startingFund = 500;
            case 2 -> startingFund = 1000;
            case 3 -> startingFund = 1500;
        }
        System.out.println("The starting fund has been set to " + startingFund + " gold");
        SettingPlayerInfo();
    }

    public void SettingPlayerInfo(){
        System.out.println("-".repeat(50));
        for (int i = 0 ; i < totalPlayers; i++){
            System.out.println("Please enter player" + (i+1) + "'s name");
            Scanner myScanner = new Scanner(System.in);
            name = myScanner.nextLine();
            players.add(new Player(name,startingFund));
        }
        System.out.println("-".repeat(50));
        System.out.println("Players are: ");
        for (int i = 0; i < totalPlayers; i++){
            System.out.println("Player" + (i+1) + ": " + players.get(i).getName() + ". Money: " + startingFund + " gold");
        }
        System.out.println("-".repeat(50));
        System.out.println("Settings has been done. The game will start now. Goodluck dear farmers!");
        try {
            Thread.sleep(2500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        mainGamePreRound();
    }

    public void mainGamePreRound(){
        while(currentRound <= totalRounds){
            while(activePlayer < players.size()){
                Player player1 = players.get(activePlayer);
                //Decrease health per round needed
                //Display detailed current player's info needed
                if(player1.isPlayerRemainingInGame){
                    mainGameRound(player1);
                }
                else{
                    System.out.println(player1.getName() + " has became bankrupt, the next player's turn will start.");
                }
                activePlayer++; //fixed a bug where player 2-4 never get to their turn, placed activePlayer++ in else column
            }
            activePlayer = 0;
            currentRound ++;
        }
    }

    public void mainGameRound(Player player){
        System.out.println("-".repeat(50));
        System.out.println("Turn: " + currentRound);
        System.out.println("Hello " + player.getName());
        userInput1 = Dialog.menu("What would you like to do?","Buy new Animals","Buy new Food","Feed my Animals",
                "Mate my Animals", "Sell my Animals");
        switch (userInput1){
            case 1 -> { store.BuyNewAnimalsMenu(player);}
            case 2 -> { store.BuyNewFoodMenu(player);}
            case 3 -> { feed(player);}
            case 4 -> { }
            case 5 -> { }
        }
    }

    public void feed(Player player) {
        for (Animal animal : player.ownedAnimal) {
            int choice = 0;
            while (choice != 4) {
                System.out.println("What would you want to feed your " + animal.getClass().getSimpleName() + "-" + animal.getName());
                choice = Dialog.menuWithOutQuestion("Corn", "Grass", "Millet", "Next Animal");

                switch (choice) {
                    case 1 -> {
                        //BUG, BOOLEAN DOES NOT WORK PROPERLY --
                        //Fixed
                        if (!(animal.getClass().getSimpleName().equalsIgnoreCase("pig"))) {
                            System.out.println("Your " + animal.getClass().getSimpleName() + " can not eat " + player.ownedFood.get(0).getClass().getSimpleName());
                        } else if (player.ownedFood.get(0).getFoodAmount() == 0) {
                            System.out.println("We are out of corn! " + animal.getClass().getSimpleName() + "-" + animal.getName() + " will starve this turn");
                            System.out.println("Get more corn!");
                        } else if (animal.getClass().getSimpleName().equalsIgnoreCase("pig") && player.ownedFood.get(0).getFoodAmount() > 0) {
                            player.ownedFood.get(0).setFoodAmount((player.ownedFood.get(0).getFoodAmount() - 1));
                            animal.setHealth((animal.getHealth() + 10));
                            System.out.println("1 kg " + player.ownedFood.get(0).getClass().getSimpleName() + " has been consumed");
                            choice = 4;
                        }
                    }
                    case 2 -> {
                        if (!(animal.getClass().getSimpleName().equalsIgnoreCase("cow") ||
                                (animal.getClass().getSimpleName().equalsIgnoreCase("horse")) ||
                                (animal.getClass().getSimpleName().equalsIgnoreCase("sheep")))) {
                            System.out.println("Your " + animal.getClass().getSimpleName() + " can not eat " + player.ownedFood.get(1).getClass().getSimpleName());
                        } else if (player.ownedFood.get(1).getFoodAmount() == 0) {
                            System.out.println("We are out of grass! " + animal.getClass().getSimpleName() + "-" + animal.getName() + " will starve this turn");
                            System.out.println("Get more grass!");
                        } else if (animal.getClass().getSimpleName().equalsIgnoreCase("cow") ||
                                (animal.getClass().getSimpleName().equalsIgnoreCase("horse")) ||
                                (animal.getClass().getSimpleName().equalsIgnoreCase("sheep")) && player.ownedFood.get(1).getFoodAmount() > 0) {

                            player.ownedFood.get(1).setFoodAmount((player.ownedFood.get(1).getFoodAmount() - 1));
                            animal.setHealth((animal.getHealth() + 10));
                            System.out.println("1 kg " + player.ownedFood.get(0).getClass().getSimpleName() + " has been consumed");
                            choice = 4;
                        }
                    }
                    case 3 -> {
                        if (!(animal.getClass().getSimpleName().equalsIgnoreCase("chicken"))) {
                            System.out.println("Your " + animal.getClass().getSimpleName() + " can not eat " + player.ownedFood.get(2).getClass().getSimpleName());
                        } else if (player.ownedFood.get(2).getFoodAmount() == 0) {
                            System.out.println("We are out of millet! " + animal.getClass().getSimpleName() + "-" + animal.getName() + " is starving!");
                            System.out.println("Get more millet!");
                        } else if (animal.getClass().getSimpleName().equalsIgnoreCase("chicken") && player.ownedFood.get(2).getFoodAmount() > 0) {
                            player.ownedFood.get(2).setFoodAmount((player.ownedFood.get(2).getFoodAmount() - 1));
                            animal.setHealth((animal.getHealth() + 10));
                            System.out.println("1 kg " + player.ownedFood.get(0).getClass().getSimpleName() + " has been consumed");
                            choice = 4;
                        }
                    }
                    case 4 ->{
                    }
                }
            }
        }
    }

    /*
    public void FeedMyAnimals(Player player){
        // Right now player doesnt need to select food to feed to animals as each animal only accept one kind of food
        // Will change at least one of the animal so player can choose what they wanna feed this animal to show that i know how to do it **
        // Change in sub animal classes needed **

        int cornConsumed = 0;
        int grassConsumed = 0;
        int milletConsumed = 0;
        // Keep track of food consumed for this turn

        for(int i = 0; i < player.ownedAnimal.size(); i++){
            int corn = player.ownedFood.get(0).getFoodAmount();
            int grass = player.ownedFood.get(1).getFoodAmount();
            int millet = player.ownedFood.get(2).getFoodAmount();
            int health = player.ownedAnimal.get(i).getHealth();
            // Get health needed **
            // Done, each time animal is fed, health +10
            // Current health should be updated each time the loop runs

            if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("CHICKEN")
                    && millet > 0){
                millet--;
                player.ownedFood.get(2).setFoodAmount(millet);
                player.ownedAnimal.get(i).setHealth(health+10);
                milletConsumed++;
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("COW")
                    && grass > 0){
                grass--;
                player.ownedFood.get(1).setFoodAmount(grass);
                player.ownedAnimal.get(i).setHealth(health+10);
                grassConsumed++;
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("Horse")
                    && grass > 0){
                grass--;
                player.ownedFood.get(1).setFoodAmount(grass);
                player.ownedAnimal.get(i).setHealth(health+10);
                grassConsumed++;
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("PIG")
                    && corn > 0){
                corn--;
                player.ownedFood.get(0).setFoodAmount(corn);
                player.ownedAnimal.get(i).setHealth(health+10);
                cornConsumed++;
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("Sheep")
                    && millet > 0){
                grass--;
                player.ownedFood.get(1).setFoodAmount(grass);
                player.ownedAnimal.get(i).setHealth(health+10);
                grassConsumed++;
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("CHICKEN")
                    && millet == 0){
                System.out.println("We are out of millet! Your Chicken "  + player.ownedAnimal.get(i).getName() + " will starve this turn, buy more food!");
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("Cow")
                    && grass == 0){
                System.out.println("We are out of grass! Your cow "  + player.ownedAnimal.get(i).getName() + " will starve this turn, buy more food!");
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("horse")
                    && grass == 0){
                System.out.println("We are out of grass! Your horse "  + player.ownedAnimal.get(i).getName() + " will starve this turn, buy more food!");
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("pig")
                    && corn == 0){
                System.out.println("We are out of corn! Your pig "  + player.ownedAnimal.get(i).getName() + " will starve this turn, buy more food!");
            }
            else if(player.ownedAnimal.get(i).getClass().getSimpleName().equalsIgnoreCase("sheep")
                    && grass == 0){
                System.out.println("We are out of grass! Your sheep "  + player.ownedAnimal.get(i).getName() + " will starve this turn, buy more food!");
            }

            System.out.println("Animals has been fed");
            System.out.println("Food consumed:");
            System.out.println("Corn: " + cornConsumed + " kg");
            System.out.println("Grass: " + grassConsumed + " kg");
            System.out.println("Millet: " + milletConsumed + " kg\n");
            cornConsumed = 0;
            grassConsumed = 0;
            milletConsumed = 0;
            // After displaying the food consumed, reset the tracker to 0 in order to function for next turn
        }
    }
    */

    /*
    private void showOwnedFood(Player player){
        System.out.println("----------Your food----------");
        for(int i = 0; i < player.getOwnedFood().size(); i++){
            System.out.println("Food: " + player.getOwnedFood().get(i) + " " + player.getOwnedFood().get(i).getFoodAmount());
        }
    }

    private void showOwnedAnimals(Player player){
        System.out.println("----------Your Animals----------");
        for(int i = 0; i < player.getOwnedAnimal().size(); i++){
            System.out.println("Animal: " + player.getOwnedAnimal().get(i).getName());
            System.out.println("Type: " + player.getOwnedAnimal().get(i).getType());
            System.out.println("Health: " + player.getOwnedAnimal().get(i).getHealth());
            System.out.println("");
        }
    }

    public int getRound() {
        return round;
    }

    public void ShowPlayerInfo(Player player) {
        Dialog.clear();
        System.out.println("Player: " + player.getName());
        System.out.println("Money: " + player.getMoney());
        int round = this.getRound();
        System.out.println("Current Round: " + round);
        System.out.println("Total Round: " + totalRounds);
        System.out.println();
        if (player.getOwnedFood().size() != 0) {
            this.showOwnedFood(player);
        }

        if (player.getOwnedAnimal().size() != 0) {
            this.showOwnedAnimals(player);
        }

        System.out.println();
    }*/
}
