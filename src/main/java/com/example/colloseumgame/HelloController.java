package com.example.colloseumgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class HelloController {
    @FXML
    private ToggleButton strengthToggle;
    @FXML
    private ToggleButton defenseToggle;
    @FXML
    private ToggleButton luckToggle;

    private String skill;

    public void strengthToggle(ActionEvent actionEvent) {
        defenseToggle.setSelected(false);
        luckToggle.setSelected(false);
        skill = "strength";
    }

    public void defenseToggle(ActionEvent actionEvent) {
        luckToggle.setSelected(false);
        strengthToggle.setSelected(false);
        skill = "defense";
    }

    public void luckToggle(ActionEvent actionEvent) {
        defenseToggle.setSelected(false);
        strengthToggle.setSelected(false);
        skill = "luck";
    }

    public void startGame(ActionEvent actionEvent) {
        Game.initialize(skill);
    }
}