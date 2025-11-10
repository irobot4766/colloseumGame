package com.example.colloseumgame;

public class Item {
    private String name;
    private int price;
    private int effect;

    public Item(String name, int price, String effect) {
        this.name = name;
        this.price = price;
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