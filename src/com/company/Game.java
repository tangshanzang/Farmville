package com.company;
import java.awt.*;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
        System.out.println("You can buy, sell, feed, breed your animals to generate profits.");
        System.out.println("As you progress, your animals can get worse health and die if they are not taken good cared off, so be careful with that!");
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

                if(player1.ownedAnimal.size() == 0 && player1.getMoney() == 0){
                    player1.isPlayerRemainingInGame = false;
                }

                //Decrease health per round needed--
                //Die when health reaches 0 needed--
                //Display detailed current player's info needed--
                //Done, these has been put in mainGameRound and decreaseHealthPreTurn;
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
        //sell all animals after last round
        Player player1;
        Player player2;
        Player player3;
        Player player4;
        Player winner;
        System.out.println("-".repeat(50));
        for (int i = 0; i < totalPlayers; i++){
            for (Animal animal : players.get(i).ownedAnimal){
                players.get(i).setMoney(players.get(i).getMoney() + (animal.getPrice() * (animal.getHealth() / 100)));
            }
            System.out.println("Player: " + players.get(i).getName());
            System.out.println("Gold: " + players.get(i).getMoney());
            System.out.println("-".repeat(50));
        }

        //Compare cash
        //Could use compare or sort, but want to do this with my current knowledge
        switch (totalPlayers){
            case 1 -> {
                player1 = players.get(0);
                winner = player1;
                System.out.println("Congratz! " + winner.getName() + " is the winner!");
                System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
            case 2 -> {
                player1 = players.get(0);
                player2 = players.get(1);
                if (player1.getMoney() > player2.getMoney())
                {
                    winner = player1;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player2.getMoney() > player1.getMoney())
                {
                    winner = player2;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else{
                    System.out.println(player1.getName() + " and " + player2.getName() + " both took 1st place!");
                    System.out.println("They both have " + player1.getMoney() + " gold");
                }
            }
            case 3 -> {
                player1 = players.get(0);
                player2 = players.get(1);
                player3 = players.get(2);
                if (player1.getMoney() > player2.getMoney() && player1.getMoney() > player3.getMoney()){
                    winner = player1;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player2.getMoney() > player1.getMoney() && player2.getMoney() > player3.getMoney()){
                    winner = player2;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player3.getMoney() > player1.getMoney() && player3.getMoney() > player2.getMoney()){
                    winner = player3;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player1.getMoney() == player2.getMoney() && player1.getMoney() == player3.getMoney()){
                    System.out.println(player1.getName() + ", " + player2.getName() + " and " +
                            player3.getName() + " all took 1st place!");
                    System.out.println("They all have " + player1.getMoney() + " gold");
                }
            }
            case 4 -> {
                player1 = players.get(0);
                player2 = players.get(1);
                player3 = players.get(2);
                player4 = players.get(3);
                if (player1.getMoney() > player2.getMoney() && player1.getMoney() > player3.getMoney() &&
                player1.getMoney() > player4.getMoney()){
                    winner = player1;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player2.getMoney() > player1.getMoney() && player2.getMoney() > player3.getMoney() &&
                player2.getMoney() > player4.getMoney()){
                    winner = player2;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player3.getMoney() > player1.getMoney() && player3.getMoney() > player2.getMoney() &&
                player3.getMoney() > player4.getMoney()){
                    winner = player3;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player4.getMoney() > player1.getMoney() && player4.getMoney() > player2.getMoney() &&
                player4.getMoney() > player3.getMoney()){
                    winner = player4;
                    System.out.println("Congratz! " + winner.getName() + " is the winner!");
                    System.out.println(winner.getName() + " has " + winner.getMoney() + " gold");
                }
                else if (player1.getMoney() == player2.getMoney() && player1.getMoney() == player3.getMoney() &&
                        player1.getMoney() == player4.getMoney()){
                    System.out.println(player1.getName() + ", " + player2.getName() + ", " +
                            player3.getName() + " and " + player4.getName() + " all took 1st place!");
                    System.out.println("They all have " + player1.getMoney() + " gold");
                }
            }
        }
    }
    public void decreaseHealthPreTurn(Player player){
        for (int i = 0; i < player.ownedAnimal.size(); i++){
            Random random = new Random();
            int number = random.nextInt(30-10 + 1) + 10;
            player.ownedAnimal.get(i).setHealth(player.ownedAnimal.get(i).getHealth()-number);//EVERY animal will get a random health decrease per turn
            System.out.println(player.ownedAnimal.get(i).getClass().getSimpleName() + " - " + player.ownedAnimal.get(i).getName() + "'s health has been decrease by " + number);

            if(player.ownedAnimal.get(i).getHealth() <= 0){
                System.out.println(player.ownedAnimal.get(i).getClass().getSimpleName() + " - " + player.ownedAnimal.get(i).getName() + " died! ");
                player.ownedAnimal.remove(player.ownedAnimal.get(i));
                i--; // Added i-- since when index i gets removed, everything moved one step to the left, so what was at index 1 is now at index 0
                // while since i++, the old index 1 would skip the loop
            }
        }
    }

    public void mainGameRound(Player player) {
        System.out.println("-".repeat(50));
        if (currentRound == totalRounds) {
            System.out.println("Turn: This is the last round!");
        } else {
            System.out.println("Turn: " + currentRound);
        }
        System.out.println("Player: " + player.getName());
        System.out.println("Gold: " + player.getMoney());
        System.out.println("-".repeat(50));
        decreaseHealthPreTurn(player); // Decrease health each turn
        Dialog.printPlayerStatus(player); // Show detailed info at round start
        userInput1 = Dialog.menu("What would you like to do?", "Buy new Animals", "Buy new Food", "Feed my Animals",
                "Breed my Animals", "Sell my Animals");
        switch (userInput1) {
            case 1 -> {
                store.BuyNewAnimalsMenu(player);
            }
            case 2 -> {
                store.BuyNewFoodMenu(player);
            }
            case 3 -> {
                feed(player);
            }
            case 4 -> {
                breedMyAnimal(player);
            }
            case 5 -> {
                sellAnimal(player);
            }
        }
    }

    public void feed(Player player) {
        for (Animal animal : player.ownedAnimal) {
            int choice = 0;
            while (choice != 4) {
                System.out.println("What would you want to feed your " + animal.getClass().getSimpleName() + " - " + animal.getName());
                choice = Dialog.menuWithOutQuestion("Corn", "Grass", "Millet", "Next Animal");

                switch (choice) {
                    case 1 -> {
                        //BUG, BOOLEAN DOES NOT WORK PROPERLY --
                        //Fixed
                        //Another way of doing it would be to set up food requirement in their class, would be easier;
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

    public void breedMyAnimal(Player player){
        int index = 1;
        boolean pass = false;
        Animal partnerA = null;
        ArrayList<Animal> partnerBList = new ArrayList<>();
        System.out.println("-".repeat(50));
        System.out.println("Your animals: ");
        for(Animal animalInList : player.ownedAnimal){
            System.out.println(index + ". " + animalInList.getClass().getSimpleName() + ". Name: " + animalInList.getName() + ", Health: " + animalInList.getHealth() + ", Gender: " + animalInList.getGender()
            + ". Breed chance left: " + animalInList.getOffSpringLeft());
            index++;
        }
        System.out.println(index + ". Leave Animal Store (Your turn will end)");
        System.out.println("-".repeat(50));

        while(!pass) {
            userInput1 = Dialog.promptInt("Which one do you want to try to breed?", "Choose an animal that is on the list!", "Numbers only!", 1, player.ownedAnimal.size() - 1);
            partnerA = player.ownedAnimal.get(userInput1-1);
            if(partnerA.offSpringLeft == 0){
                System.out.println(partnerA.getClass().getSimpleName() + " - " + partnerA.getName() + " can not breed anymore");
            }
            else if(userInput1 == index){
                pass = true;
            }
            else{
                pass = true;
            }
        }
        index = 1;
        System.out.println("-".repeat(50));
        System.out.println("These can breed with your " + partnerA.getClass().getSimpleName() + " - " + partnerA.getName() + ": ");
        for(int i = 0; i < player.ownedAnimal.size(); i++){
            if(player.ownedAnimal.get(i).getClass().equals(partnerA.getClass()) && !(player.ownedAnimal.get(i).getGender().equals(partnerA.getGender()))
            && player.ownedAnimal.get(i).offSpringLeft > 0){
                System.out.println(index + ". " + player.ownedAnimal.get(i).getClass().getSimpleName() + ". Name: " + player.ownedAnimal.get(i).getName() + ", Health: " + player.ownedAnimal.get(i).getHealth() +
                        ", Gender: " + player.ownedAnimal.get(i).getGender());
                partnerBList.add(player.ownedAnimal.get(i));
                index++;
            }
        }
        if(partnerBList.size() <= 0){
            System.out.println("You don't have any animal that can breed with " + partnerA.getClass().getSimpleName() + " - " + partnerA.getName());
        }
        else{
            System.out.println("Which one should we pair with " + partnerA.getClass().getSimpleName() + " - " + partnerA.getName() + "?");
            System.out.println("-".repeat(50));
            userInput1 = Dialog.promptIntWithOutQuestion("Choose an animal that is on the list!","Numbers only!",1,partnerBList.size());
            Animal partnerB = partnerBList.get(userInput1-1);
            //Breed Chance will decrease even if failed;
            partnerA.setOffSpringLeft(partnerA.getOffSpringLeft()-1);
            partnerB.setOffSpringLeft(partnerB.getOffSpringLeft()-1);

            //start breeding
            Random random = new Random();
            String result;
            String[] oneOrTwo = {"male","female"};
            result = oneOrTwo[random.nextInt(oneOrTwo.length)];
            //Here i decided to use same array for rolling if the partners will get an offspring or not
            //male = we made it, will get offspring, roll again for gender; female = we failed, no offspring;

            if (result.equalsIgnoreCase("male")){
                result = oneOrTwo[random.nextInt(oneOrTwo.length)];//roll again for gender
                System.out.println("-".repeat(50));
                System.out.println("Great! We got an " + result + " offspring!");

                //Here, the offspring will be added to player owned list
                if(partnerA.getClass().getSimpleName().equalsIgnoreCase("chicken")){
                    Chicken chicken = new Chicken();
                    chicken.setGender(result); // had error here because i didn't setGender even tho i rolled and printed the gender
                    chicken.setHealth(chicken.startingHealth); // Could set health as argument when creating new animal as well
                    Dialog.enterOrRandomName(chicken);
                    player.ownedAnimal.add(chicken);
                }
                else if(partnerA.getClass().getSimpleName().equalsIgnoreCase("cow")){
                    Cow cow = new Cow();
                    cow.setGender(result);
                    cow.setHealth(cow.startingHealth);
                    Dialog.enterOrRandomName(cow);
                    player.ownedAnimal.add(cow);
                }
                else if(partnerA.getClass().getSimpleName().equalsIgnoreCase("horse")){
                    Horse horse = new Horse();
                    horse.setGender(result);
                    horse.setHealth(horse.startingHealth);
                    Dialog.enterOrRandomName(horse);
                    player.ownedAnimal.add(horse);}
                else if(partnerA.getClass().getSimpleName().equalsIgnoreCase("pig")){
                    Pig pig = new Pig();
                    pig.setGender(result);
                    pig.setHealth(pig.startingHealth);
                    Dialog.enterOrRandomName(pig);
                    player.ownedAnimal.add(pig);
                }
                else if(partnerA.getClass().getSimpleName().equalsIgnoreCase("sheep")){
                    Sheep sheep = new Sheep();
                    sheep.setGender(result);
                    sheep.setHealth(sheep.startingHealth);
                    Dialog.enterOrRandomName(sheep);
                    player.ownedAnimal.add(sheep);
                }
            }
            else{
                System.out.println("Oh! The mother didn't get pregnant, we failed.");
            }
        }
        partnerBList.clear();
    }

    public void sellAnimal(Player player) {
        int menuInput = 0;
        while (menuInput != 2) {
            int index = 1;
            System.out.println("Your animals: ");
            if(player.ownedAnimal.size() == 0){
                System.out.println("You don't own any animal yet!");
            }
            else {
                for (Animal animalInList : player.ownedAnimal) {
                    System.out.println(index + ". " + animalInList.getClass().getSimpleName() + ". Name: " + animalInList.getName() + ", Health: " + animalInList.getHealth() + ", Gender: " + animalInList.getGender()
                            + ". Breed chance left: " + animalInList.getOffSpringLeft());
                    index++;
                }
            }
            menuInput = Dialog.menu("Animal Store", "Sell animal", "Leave store (Your turn will end)");

            switch (menuInput) {
                case 1 -> {
                    int userInput1 = Dialog.promptInt("Which animal do you want to sell?", "Choose an animal that is on the list!", "Numbers only!", 1, player.ownedAnimal.size() + 1);
                    int income = player.ownedAnimal.get(userInput1).getPrice() * (player.ownedAnimal.get(userInput1).getHealth() / 100);
                    System.out.println("Merchant would pay " + income + " gold");
                    System.out.println("Do you really want to sell it?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int userInput2 = Dialog.promptIntWithOutQuestion("Choose an option on the menu!", "Numbers only!", 1, 2);
                    System.out.println("-".repeat(50));
                    switch (userInput2) {
                        case 1 -> {
                            player.setMoney(player.getMoney() + income);
                            System.out.println(player.ownedAnimal.get(userInput1).getClass().getSimpleName() + " - " + player.ownedAnimal.get(userInput1).getName() + " understood that this is the last time it will meet its owner," +
                                    "as it walked by, you could almost see a tear rolling down its face.");
                            player.ownedAnimal.remove(userInput1 - 1);
                        }
                        case 2 -> { }
                    }
                }
                case 2 -> { }
            }
        }
    }
}
