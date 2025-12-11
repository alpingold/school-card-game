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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Stack;

//implement a reset method
public class BattleController implements Initializable {
    @FXML
    private Label playerHealth;
    @FXML
    private HBox cardBox,underBox;
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
    private Stack<Enemy> enemyStack;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        player = new Player("Chad Goodman",20,"Mr_goodman.png");
        enemyStack =  new Stack<>();
        Enemy ethanEnemy = new Enemy("Ethan.exe","ethanexe.png",10,1);
        Enemy alekseiEnemy = new Enemy("Aleksei(?)","alekseiSpider.png",6,3);
        Enemy nickMageEnemy = new Enemy("Nick, The Bitter","nick_mage.png",8,2);
        enemyStack.add(ethanEnemy);
        enemyStack.add(alekseiEnemy);
        enemyStack.add(nickMageEnemy);
        Collections.shuffle(enemyStack);
        enemy = enemyStack.pop();
        //i should probably not make these cards
        enemyBasic = new AttackCard("Bite", 2);
        //initial cards should be added here but like IDK how that will function yet
        ArrayList<Card> cards = new ArrayList<>();
        twoDmgCard = new AttackCard("Bash",2);
        cards.add(twoDmgCard);

        updateEnemyPicture();
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
            underBox.setDisable(false);
            Button next = new Button("Continue");
            next.setOnMouseClicked(e->{reset();});
            underBox.getChildren().add(next);
            enemyHealth.setText("Enemy HP: 0 You win!");
        }
        else {
            enemyTurn = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            actionEvent -> enemyName.setText(enemy.getName() + " is thinking...")),
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    enemyName.setText(enemy.getName() + " attacks");
                                    enemyBasic.enemyPlay(enemy, player);
                                    updatePlayerHealthLabel();
                                    statusPost.setText("-" + enemy.getDamage());
                                }
                            }),
                    new KeyFrame(Duration.seconds(2),
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
    }
    private void defeatAnimation(){
        RotateTransition rotate = new RotateTransition(Duration.millis(250), enemyPicture);
        rotate.setByAngle(15);
        rotate.setCycleCount(1);
        rotate.setAutoReverse(false);
        rotate.play();
    }
    private void updateEnemyPicture() {
        Image enemyPic = new Image(getClass().getResourceAsStream(enemy.getCardName()));
        enemyPicture.setImage(enemyPic);
    }
    public void updatePlayerNameLabel(){playerName.setText(player.getName());}
    public void updateEnemyNameLabel(){enemyName.setText(enemy.getName());}
    private void updateEnemyHealthLabel(){
        enemyHealth.setText("Enemy HP: "+enemy.getCurrentHealth());
    }
    private void reset(){
        if(!enemyStack.empty()) {
            enemy = enemyStack.pop();
            updateEnemyPicture();
            updatePlayerNameLabel();
            updateEnemyNameLabel();
            updateEnemyHealthLabel();
            updatePlayerHealthLabel();
            RotateTransition rotate = new RotateTransition(Duration.millis(200), enemyPicture);
            rotate.setByAngle(345);
            rotate.setCycleCount(1);
            rotate.setAutoReverse(false);
            rotate.play();
            attackButton.setDisable(false);
            underBox.setDisable(true);
            underBox.getChildren().clear();

        }
    }
    private void updatePlayerHealthLabel(){playerHealth.setText("Your HP: "+player.getCurrentHealth());}

}
