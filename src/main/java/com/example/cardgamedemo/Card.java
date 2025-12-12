package com.example.cardgamedemo;

import javafx.scene.image.Image;

public abstract class Card {
    private final String name;
    private final String description;
    private final int energyCost;

    public Card(String name, String description, int energyCost) {
        this.name = name;
        this.description = description;
        this.energyCost = energyCost;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getEnergyCost() { return energyCost; }

    public abstract void play(Player player, Enemy enemy);
}
