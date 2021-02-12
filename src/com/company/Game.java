package com.company;
import java.util.Scanner;

public class Game {
    int totalRounds, totalPlayers, userInput;
    boolean pass = false;

    public Game(){
        TotalRounds(5,30);
        TotalPlayers(1,4);

    }

    public int TotalRounds(int minInt, int maxInt){
        boolean bError = true;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("How many rounds will there be? (5-30)");
        do{
            try {
                totalRounds = myScanner.nextInt();
                myScanner.nextLine();
                if(totalRounds < minInt || totalRounds > maxInt){
                    System.out.println("Please enter numbers between " + minInt + " and " + maxInt);
                }
                else{
                    bError = false;
                }
            }
            catch (Exception e){
                System.out.println("You did not enter an integer, please enter an integer value");
            }
        }
        while (bError);
        System.out.println("There will be " + totalRounds + " rounds.");
        return totalRounds;
    }

    public int TotalPlayers(int minInt, int maxInt){
        boolean bError = true;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("How many players will there be? (1-4)");
        do {
            try {
                totalPlayers = myScanner.nextInt();
                myScanner.nextLine();
                if (totalPlayers < minInt || totalPlayers > maxInt) {
                    System.out.println("Please enter a number between " + minInt + " and " + maxInt);
                }
                else{
                    bError = false;
                }

            } catch (Exception e) {
                System.out.println("Please enter numbers between 1 to 4.");
            }
        }
        while (bError);
        System.out.println("There will be " + totalPlayers + " players.");
        return totalPlayers;
    }
}
