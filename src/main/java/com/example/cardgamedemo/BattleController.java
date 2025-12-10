package com.example.cardgamedemo;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
//implement a reset method
public class BattleController implements Initializable {
    @FXML
    private Label playerHealth;

    @FXML
    private HBox cardBox;
    //to-do: group objects of same types
    @FXML
    private Label statusPost;
    @FXML
    private Label enemyName;
    @FXML
    private Button attackButton;
    @FXML
    private Label playerName;
    @FXML
    private Label enemyHealth;
    @FXML
    private ImageView enemyPicture;

    private Player player;
    private Enemy enemy;
    private AttackCard twoDmgCard;
    private AttackCard enemyBasic;
    private AttackCard enemySpecial;
    private Timeline enemyTurn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        player = new Player("Chad Goodman",20);
        enemy = new Enemy("Ethan.exe",20,1);
        //i should probably not make these cards
        enemyBasic = new AttackCard("Bite", 2);
        //initial cards should be added here but like IDK how that will function yet
        ArrayList<Card> cards = new ArrayList<>();
        twoDmgCard = new AttackCard("Bash",2);
        cards.add(twoDmgCard);

        updatePlayerNameLabel();
        updateEnemyNameLabel();
        updateEnemyHealthLabel();
        updatePlayerHealthLabel();
    }
    @FXML
    void attackAction(ActionEvent event) throws InterruptedException {
        twoDmgCard.playerPlay(player,enemy);
        System.out.println(twoDmgCard.toString());
        updateEnemyHealthLabel();
        attackButton.setDisable(true);
        if(enemy.isDead()){
            defeatAnimation();
            attackButton.setDisable(true);
            Button next = new Button("Continue");
            next.setOnMouseClicked(e->{Main.switchScene("battle.fxml");});
            cardBox.getChildren().add(next);
            enemyHealth.setText("Enemy HP: 0 You win!");
        }
        else {
            enemyTurn = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            actionEvent -> enemyName.setText(enemy.getName() + " is thinking...")),
                    new KeyFrame(Duration.seconds(2),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    enemyName.setText(enemy.getName() + " attacks");
                                    enemyBasic.enemyPlay(enemy, player);
                                    updatePlayerHealthLabel();
                                    statusPost.setText("-" + enemy.getDamage());
                                }
                            }),
                    new KeyFrame(Duration.seconds(3),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    statusPost.setText("");
                                    enemyName.setText(enemy.getName());
                                    attackButton.setDisable(false);
                                }
                            })
            );
            enemyTurn.play();
        }
        //test code for battle
        /*

        */

    }
    private void defeatAnimation(){
        RotateTransition rotate = new RotateTransition(Duration.millis(250), enemyPicture);
        rotate.setByAngle(15);
        rotate.setCycleCount(1);
        rotate.setAutoReverse(false);
        rotate.play();
    }
    public void updatePlayerNameLabel(){playerName.setText(player.getName());}
    public void updateEnemyNameLabel(){enemyName.setText(enemy.getName());}
    private void updateEnemyHealthLabel(){
        enemyHealth.setText("Enemy HP: "+enemy.getCurrentHealth());
    }
    private void updatePlayerHealthLabel(){playerHealth.setText("Your HP: "+player.getCurrentHealth());}

}
