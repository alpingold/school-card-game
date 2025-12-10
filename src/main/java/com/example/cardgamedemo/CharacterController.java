package com.example.cardgamedemo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CharacterController {
    @FXML
    void backAction(ActionEvent event) {
        Main.switchScene("mainmenu.fxml");
    }
}

