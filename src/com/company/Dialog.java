package com.company;
import java.util.Scanner;
public class Dialog {

    static public Scanner myScanner = new Scanner(System.in);
    int playerMoney;
    int animalPrice;

    static public void clear(){
        System.out.println("\n".repeat(60));
    }

    static public String prompt(String question){
        System.out.println(question);
        return myScanner.nextLine();
    }

    static public int promptInt(String question, String numberOutOfRange, String invalidInput, int min, int max){
        int num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
            if(num < min || num > max){
                System.out.println(numberOutOfRange);
            }
        }
        catch(Exception ignore){System.out.println(invalidInput);}
        return num < min || num > max ?
                promptInt(question, numberOutOfRange, invalidInput, min, max) : num;
    }

    static public int menu(String menuName, String ...options){
        clear();
        System.out.println("-".repeat(50));
        System.out.println("Welcome to Farmville: " + menuName);
        System.out.println("-".repeat(50));
        int counter = 1;
        for(String option : options){
            System.out.println(counter + ". " + option);
            counter++;
        }
        System.out.println("-".repeat(50));
        // wait for the user to make a choice
        int choice = 0;
        try {
            choice = Integer.parseInt(myScanner.nextLine());
        }
        catch(Exception ignore){}
        // if illegal choice show the menu again (recursion)
        // otherwise return the choice
        return choice < 1 || choice > options.length ?
                menu(menuName, options) : choice;
    }




}
