package com.company;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
public class Dialog {

    static public Scanner myScanner = new Scanner(System.in);
    int playerMoney;
    int animalPrice;
    static boolean pass = false;

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

    public static void enterOrRandomName(Animal animal){
        System.out.println("Would you like to enter name for your " + animal.getType() + " or let it get a random name?");
        System.out.println("1. Enter name.");
        System.out.println("2. Random name.");
        String choice = myScanner.nextLine();
        switch (choice){
            case "1" ->{
                System.out.println("Please enter the name for your new " + animal.getType() + ":");
                animal.setName(myScanner.nextLine());
                System.out.println("Name for your new " + animal.getType() + " is " + animal.getName());
            }
            case "2" ->{
                while(!pass){
                    String name = randomName(animal);
                    System.out.println("Should we name it: " + name + "? ");
                    System.out.println("Yes/No");
                    choice = myScanner.nextLine();
                    if(choice.equalsIgnoreCase("Yes")){
                        animal.setName(name);
                        pass = true;
                        System.out.println("We will name it " + animal.getName());
                    }
                }
                pass = false;
            }
        }
        System.out.println("");
    }

    public static String randomName(Animal animal) {

        Random random = new Random();
        String name = "";
        if (animal.getGender().equalsIgnoreCase("Male")) {
            String[] maleNames = {
                    "Pita",
                    "Bo",
                    "LvMao",
                    "YaZi",
                    "Tian",
                    "Yang",
                    "Hao",
                    "Faker",
                    "Brandy",
                    "Viego",
                    "Maokai"
            };
            name = maleNames[random.nextInt(maleNames.length)];
            // Deleted println name, to avoid display of the name. As it is already displayed in enterOrRandomName method.
        } else if (animal.getGender().equalsIgnoreCase("Female")) {
            String[] femaleNames = {
                    "Miss Fortune",
                    "Janna",
                    "Katarina",
                    "WuLiJie",
                    "Mocha",
                    "Qiqi",
                    "Rose",
                    "LingLong",
                    "Seraphine",
                    "Leona",
                    "Miss Sundae"
            };
            name = femaleNames[random.nextInt(femaleNames.length)];
            // Deleted println name, to avoid display of the name. As it is already displayed in enterOrRandomName method.
        }
        return name;
    }
    public static void selectGender(Animal animal){
        System.out.println("Do you want a Male or Female?");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Random");
        String userInput = myScanner.nextLine();
        switch (userInput){
            case "1" -> { animal.setGender("Male");}
            case "2" -> { animal.setGender("Female");}
            case "3" -> {
                String gender[] = {"Male","Female"};
                Random random = new Random();
                String randomedGender = gender[random.nextInt(gender.length)];
                animal.setGender(randomedGender);
                System.out.println("Your new " + animal.getType() + " is a " + randomedGender);
            }
        }
    }

    public static int askForQuantity(){
        System.out.println("How many would you like to buy?");
        int input = myScanner.nextInt();
        myScanner.nextLine();
        return input;
    }

    public static int totalCost(int price, int quantity){
        int totalCost = (price * quantity);
        System.out.println("It will be " + totalCost + " gold");
        return totalCost;
    }
}







