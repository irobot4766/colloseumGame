package com.example.colloseumgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
    private ProgressBar playerHealthBar;

    @FXML
    private Label enemyHPLabel;
    @FXML
    private Label playerHPLabel;

    @FXML
    private Button leaveButton;
    @FXML
    private Label shopPriceLabel;

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
        Game.attackChoice("attack");
        updateHP();
    }

    public void blockButton(ActionEvent actionEvent) {
        Game.attackChoice("defense");
        updateHP();
    }

    public void dodgeButton(ActionEvent actionEvent) {
        Game.attackChoice("dodge");
        updateHP();
    }

    public void updateHP() {
        enemyHealthBar.setProgress(Game.enemy.getHealth()/100); //only works if enemy hp is 100
        enemyHPLabel.setText("HP : " + (int) Game.enemyHealth() + " / 100");
        playerHealthBar.setProgress(Game.player.getHealth()/100);
        playerHPLabel.setText("HP : " + (int) Game.player.getHealth() + " / 100");

        if (Game.checkEnd()) {
            if (Game.player.getHealth() > 0) {
                Game.rewardPlayer();
                returnMenu();
            }
        }
    }

    public void returnMenu() {
        leaveButton.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Battle Results");
        alert.setHeaderText("You have won the battle!");
        alert.setContentText(Game.enemy.getCoins() + " coins earned.");
        if (Math.random()*Game.player.getLuck()+3>3) {
            Game.upgradePoints++;
            alert.setContentText(alert.getContentText() + " You have been gifted 1 skill point. Use it wisely.");
        }
        alert.showAndWait();
    }

    public void leaveBattle(ActionEvent actionEvent) {
    }

    public void clickUpgrade(MouseEvent mouseEvent) {
        int tempIndex = upgradeListView.getSelectionModel().getSelectedIndex();
        shopPriceLabel.setText("Price: $" + Game.upgradePrices[tempIndex] + " OR 1 Skill Point");
    }

    public void clickItems(MouseEvent mouseEvent) {
        int tempIndex = itemListView.getSelectionModel().getSelectedIndex();
        shopPriceLabel.setText("Price: $" + Game.itemPrices[tempIndex]);
    }
}
