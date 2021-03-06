package Farmville;

import Farmville.Animal.*;
import Farmville.Food.Food;

import java.io.Serializable;

public class Store implements Serializable {

    public Store() {

    }

    int userInput1;

    public boolean enoughMoney(int cost, Player player) {
        return player.getMoney() >= cost;
    }

    public void BuyNewAnimalsMenu(Player player) {
        userInput1 = 0;
        while (userInput1 != 6) {
            userInput1 = Farmville.Dialog.menu("Animal Store", "Buy Chicken ( 10 chance to breed ) - 50 Gold per st", "Buy Cow ( 3 chance to breed ) - 250 Gold per st",
                    "Buy Horse ( 3 chance to breed ) - 250 Gold per st", "Buy Pig ( 6 chance to breed ) - 150 Gold per st", "Buy Sheep ( chance to breed ) - 150 Gold per st", "Leave Animal Store ( Your turn will end )");
            switch (userInput1) {
                case 1 -> //ADD ANIMALS!--
                        //Done, add animals to player has been added as method to BuyNewAnimals
                        BuyNewAnimals(player, Animal.Price.CHICKEN.animalPrice, userInput1);
                case 2 -> BuyNewAnimals(player, Animal.Price.COW.animalPrice, userInput1);
                case 3 -> BuyNewAnimals(player, Animal.Price.HORSE.animalPrice, userInput1);
                case 4 -> BuyNewAnimals(player, Animal.Price.PIG.animalPrice, userInput1);
                case 5 -> BuyNewAnimals(player, Animal.Price.SHEEP.animalPrice, userInput1);
                case 6 -> {
                }
            }
        }
    }

    public void BuyNewAnimals(Player player, int price, int userInput1) {
        System.out.println("Your gold: " + player.getMoney());
        int quantity = Farmville.Dialog.askForQuantity();
        int totalCost = Farmville.Dialog.totalCost(price, quantity);
        System.out.print("\n");
        if (enoughMoney(totalCost, player)) {
            for (int i = 0; i < quantity; i++) {
                player.setMoney(player.getMoney() - price);
                //RandomName, Select Gender needs to be added --
                //Done, Added to method addAnimalToPlayer.
                switch (userInput1) {
                    case 1 -> addAnimalToPlayer(player, new Chicken());
                    case 2 -> addAnimalToPlayer(player, new Cow());
                    case 3 -> addAnimalToPlayer(player, new Horse());
                    case 4 -> addAnimalToPlayer(player, new Pig());
                    case 5 -> addAnimalToPlayer(player, new Sheep());
                }
            }
            System.out.println("Trade has been done\n");
            System.out.println("Your gold: " + player.getMoney());
        } else {
            System.out.println("You don't have enough money!");
        }
    }

    public void addAnimalToPlayer(Player player, Animal animal) {

        Farmville.Dialog.selectGender(animal);//GENDER METHOD NEEDED! --
        //Done, selectGender method let user random or decide the gender of bought animals

        Farmville.Dialog.enterOrRandomName(animal);//ask for selectedName or randomName
        animal.setHealth(animal.startingHealth);//health set to startingHealth(100)
        player.ownedAnimal.add(animal);//add animal to player's animal list

    }

    public void BuyNewFoodMenu(Player player) {
        userInput1 = 0;
        while (userInput1 != 4) {
            userInput1 = Farmville.Dialog.menu("Food Store", "Buy Corn - 15 Gold per kg", "Buy Grass - 20 Gold per kg ", "Buy Millet - 10 Gold per kg", "Leave Food Store ( Your turn will end )");
            switch (userInput1) {
                case 1 -> BuyAndAddNewFood(player, Food.Price.CORN.foodPrice, userInput1);
                case 2 -> BuyAndAddNewFood(player, Food.Price.GRASS.foodPrice, userInput1);
                case 3 -> BuyAndAddNewFood(player, Food.Price.MILLET.foodPrice, userInput1);
                case 4 -> {
                }
            }
        }
    }

    public void BuyAndAddNewFood(Player player, int price, int userInput1) {
        System.out.println("Your gold: " + player.getMoney());
        int quantity = Farmville.Dialog.askForQuantity();
        int totalCost = Dialog.totalCost(price, quantity);
        System.out.print("\n");
        if (enoughMoney(totalCost, player)) {
            player.setMoney(player.getMoney() - totalCost);
            switch (userInput1) {
                case 1 -> player.ownedFood.get(0).setFoodAmount(player.ownedFood.get(0).getFoodAmount() + quantity);
                case 2 -> player.ownedFood.get(1).setFoodAmount(player.ownedFood.get(1).getFoodAmount() + quantity);
                case 3 -> player.ownedFood.get(2).setFoodAmount(player.ownedFood.get(2).getFoodAmount() + quantity);
            }
            System.out.println("Trade has been done\n");
            System.out.println("Your gold: " + player.getMoney());
        } else {
            System.out.println("You don't have enough gold for the deal");
        }
    }
}
