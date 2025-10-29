package com.example.colloseumgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class FXMLUpdate {
    @FXML
    private Tab setupTab;
    @FXML
    private ToggleButton strengthToggle;
    @FXML
    private ToggleButton defenseToggle;
    @FXML
    private ToggleButton luckToggle;

    @FXML
    private Tab shopTab;
    @FXML
    private ProgressBar strengthBar;
    @FXML
    private ProgressBar defenseBar;
    @FXML
    private ProgressBar luckBar;

    @FXML
    private ListView upgradeListView;
    @FXML
    private ListView itemListView;

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
        setupTab.setDisable(true);
        shopTab.setDisable(false);
        Game.initialize(skill);
        updateProgressBar();
        updateListViews();
    }

    public void updateProgressBar() {
        strengthBar.setProgress(Game.getStrengthP());
        defenseBar.setProgress(Game.getDefenseP());
        luckBar.setProgress(Game.getLuckP());
    }

    public void updateListViews() {
        for (String upgrade : Game.getUpgrades()) {
            upgradeListView.getItems().add(upgrade);
        }
        for (String item : Game.getItems()) {
            itemListView.getItems().add(item);
        }

    }
}
