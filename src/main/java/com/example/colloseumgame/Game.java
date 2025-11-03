package com.example.colloseumgame;

import java.util.ArrayList;

public class Game {
    public static Character player;
    public static Character enemy = new Character("Knight", 2, 2, 2);
    public static int coins;
    public static int upgradePoints;
    public static String[] upgrades = {"Strength", "Defense", "Luck"};
    public static String[] itemNames = {"Sword", "Shield", "Clover"};

    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1);
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1);
        } else {
            player = new Character("Peasant", 0, 0, 10);
        }
        player.setSkill(skill);
    }

    public static double enemyHealth() {
        return enemy.getHealth();
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

    public static double attackChoice() {
        String enemyChoice;
        enemyChoice = getEnemyChoice();
        if (enemyChoice.equals("Attack")) {
            enemy.setHealth(player.baseDamage()+(player.getSkill().equals("strength")?5:0));
        } else if (enemyChoice.equals("Block")) {
            enemy.setHealth()
        }
    }

    public static String getEnemyChoice() {
        String[] choices = {"Attack", "Block", "Dodge"};
        return choices[(int) (Math.random()*3)];
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
        for (String item : itemNames) {
            if (!player.getItemNames().contains(item)) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
