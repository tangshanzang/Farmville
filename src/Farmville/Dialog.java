package Farmville;
import Farmville.Animal.Animal;
import Farmville.Food.Food;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
public class Dialog implements Serializable {

    static public Scanner myScanner = new Scanner(System.in);
    static boolean pass = false;

    static public String prompt(String question) {
        System.out.println(question);
        return myScanner.nextLine();
    }

    static public int promptInt(String question, String numberOutOfRange, String invalidInput, int min, int max) {
        int num = min - 1;
        try {
            num = Integer.parseInt(prompt(question));
            if (num < min || num > max) {
                System.out.println(numberOutOfRange);
            }
        } catch (Exception ignore) {
            System.out.println(invalidInput);
        }
        return num < min || num > max ?
                promptInt(question, numberOutOfRange, invalidInput, min, max) : num;
    }

    static public int promptIntWithOutQuestion(String numberOutOfRange, String invalidInput, int min, int max) {
        int num = min - 1;
        try {
            //Bug, why enter does not work?
            //fixed, nextInt left enter behind it
            Scanner myScanner2 = new Scanner(System.in);
            num = myScanner2.nextInt();
            myScanner2.nextLine();
            if (num < min || num > max) {
                System.out.println(numberOutOfRange);
            }
        } catch (Exception ignore) {
            System.out.println(invalidInput);
        }
        return num < min || num > max ?
                promptIntWithOutQuestion(numberOutOfRange, invalidInput, min, max) : num;
    }

    static public int menu(String menuName, String... options) {
        System.out.println("-".repeat(50));
        System.out.println("Welcome to Farmville: " + menuName);
        System.out.println("-".repeat(50));
        int counter = 1;
        for (String option : options) {
            System.out.println(counter + ". " + option);
            counter++;
        }
        System.out.println("-".repeat(50));
        // wait for the user to make a choice
        int choice = 0;
        try {
            choice = Integer.parseInt(myScanner.nextLine());
        } catch (Exception ignore) {
        }
        // if illegal choice show the menu again (recursion)
        // otherwise return the choice
        return choice < 1 || choice > options.length ?
                menu(menuName, options) : choice;
    }

    static public int menuWithOutQuestion(String... options){
        int counter = 1;
        for (String option : options) {
            System.out.println(counter + ". " + option);
            counter++;
        }
        System.out.println("-".repeat(50));
        int choice = 0;
        try {
            choice = Integer.parseInt(myScanner.nextLine());
        } catch (Exception ignore) {
        }
        return choice < 1 || choice > options.length ?
                menuWithOutQuestion(options) : choice;
    }


    public static void enterOrRandomName(Animal animal) {
        System.out.println("Would you like to enter name for your " + animal.getClass().getSimpleName() + " or let it get a random name?");
        int choice = promptInt("1. Enter name\n2. Random name",
                "Please choose between 1 and 2", "Numbers only!", 1, 2);

        switch (choice) {
            case 1 -> {
                System.out.println("Please enter the name for your new " + animal.getClass().getSimpleName() + ":");
                animal.setName(myScanner.nextLine());
                System.out.println("Name for your new " + animal.getClass().getSimpleName() + " is " + animal.getName());
            }
            case 2 -> {
                while (!pass) {
                    String name = Dialog.randomName(animal);
                    System.out.println("Should we name it: " + name + "? ");
                    System.out.println("Yes/No (Type anything else to re-roll)");
                    String stringInput = myScanner.nextLine();
                    if (stringInput.equalsIgnoreCase("Yes")) {
                        animal.setName(name);
                        pass = true;
                        System.out.println("We will name it " + animal.getName());
                    } else if (stringInput.equalsIgnoreCase("no")) {
                        System.out.println("Please enter the name you like then");
                        animal.setName(myScanner.nextLine());
                        pass = true;
                    }
                }
                pass = false;
            }
        }
    }

        public static String randomName (Animal animal){

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
        public static void selectGender (Animal animal){
            int choice = promptInt("Do you want a Male or Female?\n1. Male\n2. Female\n3. Random","Please choose between 1 to 3","Numbers only!",
                    1,3);
            switch (choice) {
                case 1 -> animal.setGender("Male");
                case 2 -> animal.setGender("Female");
                case 3 -> {
                    String[] gender = {"Male", "Female"};
                    Random random = new Random();
                    String randomGender = gender[random.nextInt(gender.length)];
                    animal.setGender(randomGender);
                    System.out.println("Your new " + animal.getClass().getSimpleName() + " is a " + randomGender);
                }
            }
        }

        public static int askForQuantity () {
            System.out.println("How many would you like to buy?");
            int input = myScanner.nextInt();
            myScanner.nextLine();
            return input;
        }

        public static int totalCost ( int price, int quantity){
            int totalCost = (price * quantity);
            System.out.println("It will be " + totalCost + " gold");
            return totalCost;
        }

        public static void printPlayerStatus (Player player){
        int index = 1;

            System.out.println("-".repeat(50));
            System.out.println("Owned animals: ");
            if(player.ownedAnimal.size()!=0) {
                for (Animal animal : player.ownedAnimal) {
                    System.out.println(index + ". Breed: " + animal.getClass().getSimpleName() + ". Name: " + animal.getName() + ". Gender: " + animal.getGender() + ". Health: " + animal.getHealth() + ". Breed chance left: " + animal.getOffSpringLeft());
                    index++;
                }
            }
            else{
                System.out.println("You don't own any animal yet");
            }
            System.out.println("-".repeat(50));
            System.out.println("Owned food: ");
            if(player.ownedFood.size()!=0) {
                for (Food food : player.ownedFood) {
                    System.out.println("Type: " + food.getClass().getSimpleName() + ". " + food.getFoodAmount() + " kg");
                }
            }
            else{
                System.out.println("You don't own any food yet");
            }
        }
    }








