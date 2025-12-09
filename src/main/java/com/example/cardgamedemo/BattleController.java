package com.example.cardgamedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    @FXML
    private Button attackButton;

    @FXML
    private Label enemyHealth;

    private Player player;
    private Enemy enemy;
    private AttackCard twoDmgCard;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        player = new Player("Default Goodman",20);
        enemy = new Enemy("Ethan.exe",20);

        twoDmgCard = new AttackCard("Bash",2);

        updateEnemyHealthLabel();
    }
    @FXML
    void attackAction(ActionEvent event) {
        twoDmgCard.play(player,enemy);
        updateEnemyHealthLabel();

        if(enemy.isDead()){
            attackButton.setDisable(true);
            enemyHealth.setText("Enemy HP: 0 You win!");
        }
    }
    private void updateEnemyHealthLabel(){
        enemyHealth.setText("Enemy HP: "+enemy.getCurrentHealth());
    }

}
