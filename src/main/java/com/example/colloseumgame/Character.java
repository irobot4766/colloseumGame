package com.example.colloseumgame;

import java.util.ArrayList;

public class Character {
    private String name;

    private double audience;
    private int attack;
    private int defense;
    private int luck;
    private ArrayList<Item> items = new ArrayList<>();

    private double health = 100;

    public Character(String name, int attack, int defense, int luck) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.luck = luck;
    }

    public int baseDamage() {
        int bonusDamage = 0;
        if (getEffect(0)) bonusDamage += 5;
        return (int) (attack*Math.random()+bonusDamage);
    }

    public int baseDefense() {
        int bonusDefense = 0;
        if (getEffect(1)) bonusDefense += 5;
        return (int) (defense*Math.random()+bonusDefense);
    }

    public boolean getEffect(int effect) {
        for (Item item: items) {
            if (item.getEffect()==effect) return true;
        } return false;
    }
    public int blockedDamage() {
        return (int) (defense*Math.random()*(int)((double)luck/2));
    }

    public boolean successfulDodge() {
        return (Math.random()<Math.pow(luck, 0.1)-0.5);
    }

    public double getAttack() {
        return attack;
    }
    public double getLuck() {
        return luck;
    }
    public double getDefense() {
        return defense;
    }

    public ArrayList<String> getItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (Item item : items) {
            itemNames.add(item.getName());
        }
        return itemNames;
    }

}
