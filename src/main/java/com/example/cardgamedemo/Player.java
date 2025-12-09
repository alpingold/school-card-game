package com.example.cardgamedemo;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final int maxHealth;
    private int currentHealth;

    private final List<AttackCard> deck = new ArrayList<>();

    public Player(String name, int maxHealth){
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public String getName(){
        return name;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getCurrentHealth(){
        return currentHealth;
    }
    public void takeDamage(int amount){
        currentHealth = Math.max(0,currentHealth-amount);
    }
    public void heal(int amount){
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }
    //glaring issue is that this is only a deck of AttackCards
    //could either make a seperate for each or wrap AttackCard into Card class
    public List<AttackCard> getDeck(){
        return deck;
    }
    public void addCardToDeck(AttackCard card){
        deck.add(card);
    }
}
