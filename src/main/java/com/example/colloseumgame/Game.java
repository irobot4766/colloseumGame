package com.example.colloseumgame;

import java.util.ArrayList;
import java.util.Random;

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

    public static String[] enemyNames = {
            "Scourge",
            "Iron",
            "Vex",
            "Cinder",
            "Omen",
            "Aura",
            "Vandal",
            "Sable",
            "Talon",
            "Fell",
            "Praetor",
            "Bane",
            "Grit",
            "Rage",
            "Crest",
            "Spite",
            "Stone",
            "Husk",
            "Dread",
            "Gloom",
            "Warden",
            "Grim",
            "Apex",
            "Ruin",
            "Brute",
            "Tusk",
            "Shear",
            "Slayer",
            "Tide",
            "Shackle",
            "Flayer",
            "Shade",
            "Glaive",
            "Visage",
            "Wreck",
            "Thrall",
            "Aegis",
            "Stalker",
            "Viper",
            "Valor",
            "Reaver",
            "Storm",
            "Harrow",
            "Rend",
            "Cipher",
            "Quarry",
            "Specter",
            "Lacer",
            "Crush",
            "Guard"
    };

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
                player.setHealth(enemies.get(currEnemy).
                        baseDamage()+(enemies.get(currEnemy).
                        getSkill().equals("strength")?5:0));
                return ("You chose to attack. " + enemies.get(currEnemy).
                        getName() + " retaliated by attacking.");
            } else if (enemyChoice.equals("Block")) {
                double damage = player.baseDamage()+(player.getSkill().equals("strength")?5:0)-((double)enemies.get(currEnemy).
                        baseDefense()/2);
                if (damage < 0) damage = 0;
                enemies.get(currEnemy).
                        setHealth(damage*2);
                player.setHealth(enemies.get(currEnemy).blockedDamage());
                return enemies.get(currEnemy).getName() + " tried to block you from dodging. You attacked him instead.";
            } else {
                if (enemies.get(currEnemy).
                        successfulDodge()) {
                    player.setHealth(player.getHealth()*.50);
                    return "You tried to attack, but miserably failed. You have been brutally wounded.";
                } else {
                    enemies.get(currEnemy).
                            setHealth(player.baseDamage() + (player.getSkill().equals("strength") ? 5 : 0));
                    return enemies.get(currEnemy).
                            getName() + " tried to dodge your attack, but ate it anyway.";
                }
            }
        } else if (choice.equals("defense")) {
            if (enemyChoice.equals("Attack")) {
                double damage = enemies.get(currEnemy).
                        baseDamage()+(enemies.get(currEnemy).
                        getSkill().equals("strength")?5:0)-player.baseDefense();
                if (damage < 0) damage = 0;
                player.setHealth(damage);
                enemies.get(currEnemy).
                        setHealth((double) (player.baseDefense() + (player.getSkill().equals("defense") ? 5 : 0)) / 2);
                return "You tried to block the enemy from dodging. He attacked you instead.";
            } else if (enemyChoice.equals("Block")) {
                enemies.get(currEnemy).
                        setHealth(player.baseDefense()+(player.getSkill().equals("defense")?5:0));
                player.setHealth(enemies.get(currEnemy).
                        baseDefense()+(enemies.get(currEnemy).
                        getSkill().equals("defense")?5:0));
                return "You tried to block " + enemies.get(currEnemy).getName() + " however he had the same idea.";
            } else {
                if (enemies.get(currEnemy).
                        successfulDodge()) { //if he tries to dodge
                    enemies.get(currEnemy).
                            setHealth(enemies.get(currEnemy).
                                    getHealth()*.15);
                    return enemies.get(currEnemy).getName() + " tries to dodge, however you grab him. He still manages to escape from your grip.";
                } else {
                    enemies.get(currEnemy).
                            setHealth(enemies.get(currEnemy).
                                    getHealth()*.30);
                    return enemies.get(currEnemy).getName() + " tries to dodge, however you grab him. You deal a devastating amount of damage.";

                }
            }
        } else {
            if (enemyChoice.equals("Attack")) {
                if (player.successfulDodge()) {
                    enemies.get(currEnemy).
                            setHealth(enemies.get(currEnemy).
                                    getHealth()*.50);
                    return "You successfully dodged " + enemies.get(currEnemy).getName()+"'s attack and left him injured.";

                } else {
                    player.setHealth(enemies.get(currEnemy).
                            baseDamage() + (enemies.get(currEnemy).
                            getSkill().equals("strength") ? 5 : 0));
                    return "You attempted to dodge " + enemies.get(currEnemy).getName()+ "'s attack however got dusted";
                }
            } else if (enemyChoice.equals("Block")) {
                if (player.successfulDodge()) {
                    player.setHealth(player.getHealth()*.15);
                    return "You barely escaped from the grips of " + enemies.get(currEnemy).getName();
                } else {
                    player.setHealth(player.getHealth()*.30);
                    return enemies.get(currEnemy).getName() + " left you begging for your life after you missed your dodge.";

                }
            } else {
                //but nothing happened
                return "But nothing happened...";
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

    public static String getCurrItems() {
        String items = "";
        for (String item: player.getItemNames()) {
            items += item + ", ";
        }
        items = items.substring(0, items.length()-2);
        return items;
    }

    public static boolean checkEnd() {
        return player.getHealth() <= 0 || enemies.get(currEnemy).
                getHealth() <= 0;
    }

    public static void rewardPlayer() {
        player.setCoins(enemies.get(currEnemy).
                getCoins());
        player.setHealth(100);
        player.resetHealth();
        player.resetItems();
    }

    public static void nextPlayer() {
        if (currEnemy == 4) {
            enemies.add(new Character(enemyNames[(int) (Math.random()*enemyNames.length)], (int) (Math.random()*5) + currEnemy*2, (int) (Math.random()*5) + currEnemy*2, (int) (Math.random()*5) + currEnemy*2, "strength", 30));
        }
        currEnemy++;
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
        player.setCoins(-itemPrices[itemN]);
    }
}
