package com.example.cardgamedemo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {
    @FXML
    void backAction(ActionEvent event) {
        Main.switchScene("mainmenu.fxml");
    }
    public void initialize(URL location, ResourceBundle resources){
        //make the characters

    }
}

