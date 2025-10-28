package com.example.colloseumgame;

public class Game {
    public static Character player;

    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1);
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1);
        } else {
            player = new Character("Peasant", 0, 0, 10);
        }
    }
}
