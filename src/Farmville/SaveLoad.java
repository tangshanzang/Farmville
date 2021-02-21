package Farmville;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveLoad implements Serializable {
    String saveSlot, savePathInString;

    private Game myGame;

    public SaveLoad(Game myGame) {
        this.myGame = myGame;
    }

    public void saveGame() {
        boolean pass = false;
        while (!pass) {
            int number = Dialog.promptInt("Please choose a save:\n1.Save1\n2.Save2\n3.Save3\n4.Exit", "Choose a save on the menu", "Numbers only!", 1, 4);
            switch (number) {
                case 1 -> saveSlot = "Save1";
                case 2 -> saveSlot = "Save2";
                case 3 -> saveSlot = "Save3";
                case 4 -> pass = true;
            }
            savePathInString = saveSlot + ".ser";
            Path savePath = Paths.get(savePathInString);
            boolean exist = Files.exists(savePath);
            if (!exist) {

                Serializer.serialize(savePathInString, myGame);
                System.out.println("Game saved");
                pass = true;
            }
            else{
                number = Dialog.promptInt("Save is occupied, do you want to overwrite the save?\n1.Yes\n2.No"
                        , "Choose a save on the menu", "Numbers only!", 1, 2);
                switch (number) {
                    case 1 -> { Serializer.serialize(savePathInString, myGame);
                        System.out.println("Game saved");
                        pass = true;
                    }

                    case 2 -> {
                    }
                }
            }
        }
    }

    public void loadGame() {
        System.out.println("-".repeat(50));
        boolean pass = false;
        while (!pass) {
            System.out.println("Select the save file you would like to load:");
            System.out.println("1.Save1 ");
            System.out.println("2.Save2 ");
            System.out.println("3.Save3 ");
            System.out.println("4.Exit ");
            Scanner myScanner = new Scanner(System.in);
            String input = myScanner.nextLine();
            System.out.println("-".repeat(50));

            switch (input) {
                case "1" -> {
                    if (!Files.exists(Paths.get("Save1.ser"))) {
                        System.out.println("Save is empty");
                    } else {
                        myGame = (Game) Serializer.deserialize("Save1.ser");
                        myGame.mainGamePreRound();
                        pass = true;
                    }
                }
                case "2" -> {
                    if (!Files.exists(Paths.get("Save2.ser"))) {
                        System.out.println("Save is empty");
                    } else {
                        myGame = (Game) Serializer.deserialize("Save2.ser");
                        myGame.mainGamePreRound();
                        pass = true;
                    }
                }
                case "3" -> {
                    if (!Files.exists(Paths.get("Save3.ser"))) {
                        System.out.println("Save is empty");
                    } else {
                        myGame = (Game) Serializer.deserialize("Save3.ser");
                        myGame.mainGamePreRound();
                        pass = true;
                    }
                }
                case "4" -> pass = true;
            }
        }
    }
}
