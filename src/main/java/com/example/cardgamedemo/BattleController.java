package com.example.cardgamedemo;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {
    @FXML
    private Label playerHealth;

    @FXML
    private Button attackButton;

    @FXML
    private Label enemyHealth;
    @FXML
    private ImageView enemyPicture;

    private Player player;
    private Enemy enemy;
    private AttackCard twoDmgCard;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        player = new Player("Default Goodman",20);
        enemy = new Enemy("Ethan.exe",20);

        twoDmgCard = new AttackCard("Bash",2);

        updateEnemyHealthLabel();
        updatePlayerHealthLabel();
    }
    @FXML
    void attackAction(ActionEvent event) {
        twoDmgCard.play(player,enemy);
        updateEnemyHealthLabel();

        if(enemy.isDead()){
            defeatAnimation();
            attackButton.setDisable(true);
            enemyHealth.setText("Enemy HP: 0 You win!");
        }
    }
    private void defeatAnimation(){
        RotateTransition rotate = new RotateTransition(Duration.millis(250), enemyPicture);
        rotate.setByAngle(15);
        rotate.setCycleCount(1);
        rotate.setAutoReverse(false);
        rotate.play();
    }
    private void updateEnemyHealthLabel(){
        enemyHealth.setText("Enemy HP: "+enemy.getCurrentHealth());
    }
    private void updatePlayerHealthLabel(){playerHealth.setText("Your HP: "+player.getCurrentHealth());}

}
