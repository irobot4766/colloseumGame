package com.example.colloseumgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private Tab battleTab;
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

    @FXML
    private Label enemyName;
    @FXML
    private ProgressBar enemyHealthBar;
    @FXML
    private ProgressBar playerHealtBar;

    @FXML
    private Label enemyHPLabel;
    @FXML
    private Label playerHPlabel;

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

    public void startBattle(ActionEvent actionEvent) {
        shopTab.setDisable(true);
        battleTab.setDisable(false);
        enemyName.setText("Opponent: " + Game.enemyName());
    }

    public void attackButton(ActionEvent actionEvent) {
        Game.attackChoice();
        enemyHealthBar.setProgress(Game.enemyHealth()/100); //only works if enemy hp is 100
        enemyHPLabel.setText("HP : " + (int) Game.enemyHealth() + " / 100");
    }

    public void blockButton(ActionEvent actionEvent) {
    }

    public void dodgeButton(ActionEvent actionEvent) {
    }
}
