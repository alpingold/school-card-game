package com.example.cardgamedemo;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Stack;

//note to self: ALEKSEI WTF!!! THIS IS A DISGUSTING MESS OF A CONTROLLER
public class BattleController implements Initializable {
    @FXML
    private Label playerHealthLabel;
    @FXML
    private ProgressBar playerHealthBar;
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
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discordPile;
    private ArrayList<Card> hand;

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
        //card system beta
        drawPile = new ArrayList<>();
        discordPile = new ArrayList<>();
        hand = new ArrayList<>();
        //add a shit ton of filler cards for now. im not creative enough to make more rn
        drawPile.add(new AttackCard("Bash","smash ur skull into enemy",1,1));
        drawPile.add(new AttackCard("Kreation Punch","recall taekwando basics",1,2));
        drawPile.add(new AttackCard("Pencil Sword","stab with the unsharpened pencil", 2,1));
        drawPile.add(new AttackCard("Kick of Nick D.","imitate the legendary kick",2,5));

        Collections.shuffle(drawPile);

        drawCards(3);
        refreshHandUI();
        //i should probably not make these cards
        enemyBasic = new AttackCard("Bite","sink teeth in",2, 2);

        updateEnemyPicture();
        updatePlayerNameLabel();
        updateEnemyNameLabel();
        updateEnemyHealthLabel();
        updatePlayerHealthLabel();
    }

    //card logic
    private void drawCards(int amount){
        for(int i=0;i<amount;i++){
            if(drawPile.isEmpty()){
                if(discordPile.isEmpty()){
                    break;
                }
                drawPile.addAll(discordPile);
                discordPile.clear();
                Collections.shuffle(drawPile);
            }
            Card takenCard = drawPile.removeLast();
            hand.add(takenCard);
        }
    }
    private void playCard(Card card){
        card.play(player,enemy);
        hand.remove(card);
        discordPile.add(card);
        updateEnemyHealthLabel();
        //updatePlayerHealthLabel();
        if(enemy.isDead()){
            defeatAnimation();
            attackButton.setDisable(true);
            underBox.setDisable(false);
            Button next = new Button("Continue");
            next.setOnMouseClicked(e->{reset();});
            underBox.getChildren().add(next);
            enemyHealth.setText("Enemy HP: 0 You win!");
        }
        else{
            handleEnemyTurn();
        }
        refreshHandUI();
    }
    public void refreshHandUI(){
        cardBox.getChildren().clear();
        for(Card card : hand){
            Button cardButton = new Button();
            //center properly with a property THIS IS JUST TEST CODE
            cardButton.setText(card.getName()+"\n"+card.getDescription());
            cardButton.setWrapText(true);
            cardButton.setPrefWidth(150);
            cardButton.setPrefHeight(200);
            cardButton.setOnAction(e ->playCard(card));
            cardBox.getChildren().add(cardButton);
        }
    }
    public void handleEnemyTurn(){
        enemyTurn = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            actionEvent -> enemyName.setText(enemy.getName() + " is thinking...")),
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    enemyName.setText(enemy.getName() + " attacks");
                                    enemyBasic.play(enemy, player);
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
    private void updatePlayerHealthLabel(){
        int current = player.getCurrentHealth();
        int max = player.getMaxHealth();
        double progress = (double) current/max;
        playerHealthBar.setProgress(progress);
        playerHealthLabel.setText(current+"/"+max);}

}
