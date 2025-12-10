package com.example.cardgamedemo;

public class Enemy {

    private final String name;
    private final String cardName;
    private final int maxHealth;
    private int currentHealth;
    private int damage;

    public Enemy(String name, String cardName, int maxHealth, int damage) {
        this.cardName = cardName;
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = 1;
    }
    public String getCardName() {
        return cardName;
    }
    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void takeDamage(int amount) {
        currentHealth = Math.max(0, currentHealth - amount);
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public int getDamage() {
        return damage;
    }
}
