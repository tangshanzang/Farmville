package Farmville;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveLoad implements Serializable {
    String saveSlot, savePathInString;

    private Game myGame;

    public SaveLoad(Game myGame) {
        this.myGame = myGame;
    }

    public void saveGame() {
        boolean pass = false;
        while (!pass) {
            saveSlot = Dialog.prompt("Please enter a name for your save");
            String root = "saves/";
            savePathInString = root + saveSlot.toLowerCase() + ".ser";
            Path savePath = Paths.get(savePathInString);
            boolean exist = Files.exists(savePath);
            if (!exist) {
                Serializer.serialize(savePathInString, myGame);
                System.out.println("Game saved");
                pass = true;
            } else {
                int number = Dialog.promptInt("Save is occupied, do you want to overwrite the save?\n1.Yes\n2.No"
                        , "Choose an option on the menu", "Numbers only!", 1, 2);
                switch (number) {
                    case 1 -> {
                        Serializer.serialize(savePathInString, myGame);
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
        boolean pass = false;
        File folderPath = new File("saves/");
        FilenameFilter serFilter = new FilenameFilter() { // Create a filter to find every file that ends with .ser
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".ser");
            }
        };
        String[] serFiles = folderPath.list(serFilter); // find .ser in saves folder
        while (!pass) {
            if (serFiles != null && serFiles.length > 0) {
                System.out.println("Your saves : ");
                for (String name : serFiles)
                    System.out.println(name.replaceAll(".ser", "")); // remove .ser to look user friendly
                System.out.println("-".repeat(50));
                String a = Dialog.prompt("Enter name of the save you would want to load.");
                String loadString = "saves/" + a.toLowerCase() + ".ser";
                File tryPath = new File(loadString);
                if (tryPath.exists()) {
                    pass = true;
                    System.out.println("Game loaded");
                    myGame = (Game) Serializer.deserialize(loadString);
                    myGame.mainGamePreRound();
                } else {
                    System.out.println("Save does not exist, please make sure you entered the correct name for the save");
                    System.out.println("-".repeat(50));
                    int choice = Dialog.promptInt("1. Try again\n2. Exit","Select an option on the menu!","Numbers only!",1,2);
                    switch (choice){
                        case 1 -> {}
                        case 2 -> pass = true;
                    }
                }
            }
            else{
                System.out.println("You don't have any save!");
                pass = true;
            }
        }
    }
}


