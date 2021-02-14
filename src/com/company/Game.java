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
                //Decrease health per round.
                if(player1.isPlayerRemainingInGame){
                    mainGameRound(player1);
                }
                else{
                    System.out.println(player1.getName() + " has became bankrupt, the next player's turn will start.");
                    activePlayer++;
                }
                activePlayer = 0;
                currentRound ++;
            }
        }
    }

    public void mainGameRound(Player player){
        System.out.println("-".repeat(50));
        System.out.println("Turn: " + currentRound);
        System.out.println("Hello " + player);
        userInput1 = Dialog.menu("What would you like to do?","Buy new Animals","Buy new Food","Feed my Animals",
                "Mate my Animals", "Sell my Animals");
        switch (userInput1){
            case 1 -> { store.BuyNewAnimalsMenu(player);}
            case 2 -> { }
            case 3 -> { }
            case 4 -> { }
            case 5 -> { }
        }
    }

    public int getRound() {
        return round;
    }

    private void showOwnedFood(Player player){
        System.out.println("----------Your food----------");
        for(int i = 0; i < player.getOwnedFood().size(); i++){
            System.out.println("Food: " + player.getOwnedFood().get(i) + " " + player.getOwnedFood().get(i).FoodAmount());
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
    }

}
