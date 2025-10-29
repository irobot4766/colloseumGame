package com.example.colloseumgame;

public class Character {
    private String name;

    private double audience;
    private int attack;
    private int damage;
    private int defense;
    private int luck;

    private double health;

    public Character(String name, int attack, int defense, int luck) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.luck = luck;
    }

    public int baseDamage() {
        return (int) (attack*Math.random()+damage);
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

}
