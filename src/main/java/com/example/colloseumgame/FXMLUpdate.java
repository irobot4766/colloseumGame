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
    private Label coinsID;
    @FXML
    private Label skillsID;

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
    private Button returnID;
    @FXML
    private Label shopPriceLabel;

    @FXML
    private Button attackID;
    @FXML
    private Button blockID;
    @FXML
    private Button dodgeID;
    @FXML
    private Label resultsID;
    @FXML
    private Label itemsID;

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
        updateLabels();
        updateProgressBar();
        updateListViews();
    }

    public void updateProgressBar() {
        strengthBar.setProgress(Game.getStrengthP());
        defenseBar.setProgress(Game.getDefenseP());
        luckBar.setProgress(Game.getLuckP());
    }

    public void updateListViews() {
        upgradeListView.getItems().clear();
        itemListView.getItems().clear();
        for (String upgrade : Game.getUpgrades()) {
            upgradeListView.getItems().add(upgrade);
        }
        for (String item : Game.getItems()) {
            itemListView.getItems().add(item);
        }

    }

    public void startBattle(ActionEvent actionEvent) {
        shopTab.setDisable(true);
        returnID.setVisible(false);
        battleTab.setDisable(false);
        attackID.setDisable(false);
        blockID.setDisable(false);
        dodgeID.setDisable(false);
        enemyName.setText("Opponent: " + Game.enemyName());
        resultsID.setText("");
        updateHP();
    }

    public void attackButton(ActionEvent actionEvent) {
        resultsID.setText(Game.attackChoice("attack"));
        updateHP();
    }

    public void blockButton(ActionEvent actionEvent) {
        resultsID.setText(Game.attackChoice("defense"));
        updateHP();
    }

    public void dodgeButton(ActionEvent actionEvent) {
        resultsID.setText(Game.attackChoice("luck"));
        updateHP();
    }

    public void updateHP() {
        enemyHealthBar.setProgress(Game.enemies.get(Game.currEnemy).getHealth()/100); //only works if enemy hp is 100
        enemyHPLabel.setText("HP : " + (int) Game.enemyHealth() + " / 100");
        playerHealthBar.setProgress(Game.player.getHealth()/100);
        playerHPLabel.setText("HP : " + (int) Game.player.getHealth() + " / 100");

        if (Game.checkEnd()) {
            System.out.println("game shouldve ended brosquito");
            if (Game.player.getHealth() > Game.enemies.get(Game.currEnemy).getHealth()) {
                Game.rewardPlayer();
                Game.nextPlayer();
                returnMenu();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Battle Results");
                alert.setHeaderText("You have LOST the battle!");
                alert.setContentText("Try again next time...");
                alert.showAndWait();
                battleTab.setDisable(true);
            }
        }
    }


    public void returnMenu() {
        attackID.setDisable(true);
        blockID.setDisable(true);
        dodgeID.setDisable(true);
        returnID.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Battle Results");
        alert.setHeaderText("You have won the battle!");
        alert.setContentText(Game.enemies.get(Game.currEnemy).getCoins() + " coins earned.");
        if (Math.random()*Game.player.getLuck()>3) {
            Game.upgradePoints++;
            alert.setContentText(alert.getContentText() + " You have been gifted 1 skill point. Use it wisely.");
        }
        alert.showAndWait();
    }

    public void leaveBattle(ActionEvent actionEvent) {
        battleTab.setDisable(true);
        shopTab.setDisable(false);
        itemsID.setText("Items are only usable for one battle");
        updateLabels();
    }

    public void clickUpgrade(MouseEvent mouseEvent) {
        int tempIndex = upgradeListView.getSelectionModel().getSelectedIndex();
        if (tempIndex > -1) shopPriceLabel.setText("Price: $" + Game.upgradePrices[tempIndex] + " OR 1 Skill Point");
    }

    public void clickItems(MouseEvent mouseEvent) {
        int tempIndex = itemListView.getSelectionModel().getSelectedIndex();
        if (tempIndex > -1) shopPriceLabel.setText("Price: $" + Game.itemPrices[tempIndex]);
    }

    public void updateLabels() {
        skillsID.setText("Skill Points: " + Game.upgradePoints);
        coinsID.setText("Coins: " + Game.player.getCoins());

    }


    public void upgradeButton(ActionEvent actionEvent) {
        int tempIndex = upgradeListView.getSelectionModel().getSelectedIndex();
        if (tempIndex > -1) {
            Game.upgradeSkill(tempIndex);
            updateLabels();
            shopPriceLabel.setText("Price:");
            updateProgressBar();
        }

    }

    public void buyButton(ActionEvent actionEvent) {
        int tempIndex = itemListView.getSelectionModel().getSelectedIndex();
        System.out.println("test");
        System.out.println(tempIndex);
        if (tempIndex > -1) {
            Game.addItem(tempIndex);
            shopPriceLabel.setText("Price:");
            itemsID.setText("Item Buffs: " + Game.getCurrItems());
            updateLabels();
            updateProgressBar();
            updateListViews();
        }

    }
}
