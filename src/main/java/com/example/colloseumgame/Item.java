package com.example.colloseumgame;

public class Item {
    private String name;
    private int effect;

    public Item(String name, String effect) {
        this.name = name;
        if (effect.equals("Sword")) this.effect = 0;
        if (effect.equals("Shield")) this.effect = 1;
        if (effect.equals("Clover")) this.effect = 2;
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }
}