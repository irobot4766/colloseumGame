package com.example.colloseumgame;

public class Game {
    public static Character player;
    public static Character enemy;

    public static void initialize(String skill) {
        if (skill.equals("strength")) {
            player = new Character("Peasant", 5, 3, 1);
        } else if (skill.equals("defense")) {
            player = new Character("Peasant", 3, 5, 1);
        } else {
            player = new Character("Peasant", 0, 0, 10);
        }

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

    public static void shopLogic() {

    }
}
