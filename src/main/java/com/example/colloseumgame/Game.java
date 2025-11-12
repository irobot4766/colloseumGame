package com.example.colloseumgame;

import java.util.ArrayList;

public class
Game {
    public static Character player;
    public static ArrayList<Character> enemies = new ArrayList<>(5);
    public static int currEnemy = 0;
    public static int upgradePoints;
    public static String[] upgrades = {"Strength", "Defense", "Luck"};
    public static int[] upgradePrices = {15, 15, 15};
    public static String[] itemNames = {"Sword", "Shield", "Clover"};
    public static int[] itemPrices = {30, 25, 15};


    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1, "strength", 50);
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1, "defense", 50);
        } else {
            player = new Character("Peasant", 0, 0, 10, "luck", 50);
        }

        enemies.add(new Character("Homeless Man", 1, 1, 1, "luck", 5));
        enemies.add(new Character("Guard", 2, 3, 2, "defense", 15));
        enemies.add(new Character("Knight", 5, 5, 3, "luck", 25));
        enemies.add(new Character("Prince", 7, 5, 8, "strength", 35));
        enemies.add(new Character("Goliath", 10, 10, 7, "strength", 50));
    }

    public static String enemyName() {return enemies.get(currEnemy).getName();}

    public static double enemyHealth() {
        return enemies.get(currEnemy).getHealth();
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


    //rps
    //dodge slams on attack
    //block slams on dodge
    //attack slams on block

    public static String attackChoice(String choice) {
        String enemyChoice;
        enemyChoice = getEnemyChoice();
        if (choice.equals("attack")) {
            if (enemyChoice.equals("Attack")) {
                enemies.get(currEnemy).setHealth(player.baseDamage()+(player.getSkill().equals("strength")?5:0));
                player.setHealth(enemy.baseDamage()+(enemy.getSkill().equals("strength")?5:0));
                return ("You chose to attack. " + enemy.getName() + " retaliated by attacking.");
            } else if (enemyChoice.equals("Block")) {
                double damage = player.baseDamage()+(player.getSkill().equals("strength")?5:0)-((double)enemy.baseDefense()/2);
                if (damage < 0) damage = 0;
                enemy.setHealth(damage*2);
                player.setHealth((double) (enemy.baseDefense() + (enemy.getSkill().equals("defense") ? 5 : 0)) / 2);
                return enemy.getName() + " tried to block you from dodging. You attacked him instead.";
            } else {
                if (enemy.successfulDodge()) {
                    player.setHealth(player.getHealth()*.33);
                    return "You tried to attack, but miserably failed. You have been brutally wounded.";
                } else {
                    enemy.setHealth(player.baseDamage() + (player.getSkill().equals("strength") ? 5 : 0));
                    return enemy.getName() + " tried to dodge your attack, but ate it anyway.";
                }
            }
        } else if (choice.equals("defense")) {
            if (enemyChoice.equals("Attack")) {
                double damage = enemy.baseDamage()+(enemy.getSkill().equals("strength")?5:0)-player.baseDefense();
                if (damage < 0) damage = 0;
                player.setHealth(damage);
                enemy.setHealth((double) (player.baseDefense() + (player.getSkill().equals("defense") ? 5 : 0)) / 2);
            } else if (enemyChoice.equals("Block")) {
                enemy.setHealth(player.baseDefense()+(player.getSkill().equals("defense")?5:0));
                player.setHealth(enemy.baseDefense()+(enemy.getSkill().equals("defense")?5:0));
            } else {
                if (enemy.successfulDodge()) { //if he tries to dodge
                    enemy.setHealth(enemy.getHealth()*.15);
                } else {
                    enemy.setHealth(enemy.getHealth()*.30);
                }
            }
        } else {
            if (enemyChoice.equals("Attack")) {
                if (player.successfulDodge()) {
                    enemy.setHealth(enemy.getHealth()*.33);
                } else {
                    player.setHealth(enemy.baseDamage() + (enemy.getSkill().equals("strength") ? 5 : 0));
                }
            } else if (enemyChoice.equals("Block")) {
                if (player.successfulDodge()) {
                    player.setHealth(player.getHealth()*.15);
                } else {
                    player.setHealth(player.getHealth()*.30);
                }
            } else {
                //but nothing happened
                return "But nothing happened...";
            }

        }
    return "";
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

    public static boolean checkEnd() {
        return player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }

    public static void rewardPlayer() {
        player.setCoins(enemy.getCoins());
        player.setHealth(100);
        player.resetHealth();

    }

    public static void upgradeSkill(int skill) {
        if (player.getCoins()>=upgradePrices[skill]||upgradePoints>0) {
            player.upgrade(upgrades[skill]);
            if (upgradePoints>0) {
                upgradePoints--;
            } else {
                player.setCoins(-upgradePrices[skill]);
                upgradePrices[skill] += 5;
            }
        }
    }

    public static void addItem(int itemN) {
        if (itemN == 0) player.giveItem("Sword");
        if (itemN == 1) player.giveItem("Shield");
        if (itemN == 2) player.giveItem("Clover");
    }
}
