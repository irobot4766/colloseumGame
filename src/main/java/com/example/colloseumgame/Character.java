package com.example.colloseumgame;

public class Character {
    private String name;

    private int attack;
    private int defense;
    private int luck;

    private double health;

    public Character(String name, int attack, int defense, int luck) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.luck = luck;
    }
}
