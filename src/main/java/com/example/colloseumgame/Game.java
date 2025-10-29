package com.example.colloseumgame;

import java.util.ArrayList;

public class Game {
    public static Character player;
    public static Character enemy;
    public static String[] upgrades = {"Strength", "Defense", "Luck"};
    public static String[] items = {"Sword", "Shield", "Clover"};

    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1);
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1);
        } else {
            player = new Character("Peasant", 0, 0, 10);
        }

    }

    public static double getStrengthP() {
        return player.getAttack()/10;
    }
    public static double getDefenseP() {
        return player.getDefense()/10;
    }
    public static double getLuckP() {
        return player.getLuck()/10;
    }

    public static void shopLogic() {

    }

    public static ArrayList<String> getUpgrades() {
        int[] upgradeLevels = {(int) player.getAttack(), (int) player.getDefense(), (int) player.getLuck()};
        ArrayList<String> upgradeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (upgradeLevels[i] < 10) upgradeList.add(upgrades[i]);
        }
        return upgradeList;
    }

    public static ArrayList<String> getItems() {
        ArrayList<String> itemList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (items[i].equals(player.getItemNames()[i])) itemList.add(items[i]);
        }
        return itemList;
    }
}
