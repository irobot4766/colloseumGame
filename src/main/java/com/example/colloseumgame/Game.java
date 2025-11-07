package com.example.colloseumgame;

import java.util.ArrayList;

public class Game {
    public static Character player;
    public static Character enemy = new Character("Knight", 3, 5, 3, "defense");
    public static int coins;
    public static int upgradePoints;
    public static String[] upgrades = {"Strength", "Defense", "Luck"};
    public static String[] itemNames = {"Sword", "Shield", "Clover"};

    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1, "strength");
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1, "defense");
        } else {
            player = new Character("Peasant", 0, 0, 10, "luck");
        }
    }

    public static String enemyName() {return enemy.getName();}

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


    //rps
    //dodge slams on attack
    //block slams on dodge
    //attack slams on block

    public static void attackChoice(String choice) {
        String enemyChoice;
        enemyChoice = getEnemyChoice();
        if (choice.equals("attack")) {
            if (enemyChoice.equals("Attack")) {
                enemy.setHealth(player.baseDamage()+(player.getSkill().equals("strength")?5:0));
                player.setHealth(enemy.baseDamage()+(enemy.getSkill().equals("strength")?5:0));
            } else if (enemyChoice.equals("Block")) {
                double damage = player.baseDamage()+(player.getSkill().equals("strength")?5:0)-((double)enemy.baseDefense()/2);
                if (damage < 0) damage = 0;
                enemy.setHealth(damage*2);
                player.setHealth((double) (enemy.baseDefense() + (enemy.getSkill().equals("defense") ? 5 : 0)) / 2);
            } else {
                if (Math.random()*enemy.getLuck() > 3) {
                    player.setHealth(player.getHealth()*.33);
                } else {
                    enemy.setHealth(player.baseDamage() + (player.getSkill().equals("strength") ? 5 : 0));
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
                if (Math.random()*enemy.getLuck() > 5) { //if he tries to dodge
                    enemy.setHealth(enemy.getHealth()*.15);
                } else {
                    enemy.setHealth(enemy.getHealth()*.30);
                }
            }
        } else {
            if (enemyChoice.equals("Attack")) {
                if (Math.random()*player.getLuck() > 3) {
                    enemy.setHealth(enemy.getHealth()*.33);
                } else {
                    player.setHealth(enemy.baseDamage() + (enemy.getSkill().equals("strength") ? 5 : 0));
                }
            } else if (enemyChoice.equals("Block")) {
                if (Math.random()*player.getLuck() > 5) {
                    player.setHealth(player.getHealth()*.15);
                } else {
                    player.setHealth(player.getHealth()*.30);
                }
            } else {
                //but nothing happened
                return;
            }

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
