package com.example.cardgamedemo;

import javafx.scene.image.Image;

public class Card {
    private final String name;
    //private final int width;
    //private final int height;
    //private final Image image;
//    public Card(String name, int width, int height) {
//        this.name = name;
//        this.width = width;
//        this.height = height;
//    }



    public Card(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
