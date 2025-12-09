package com.example.cardgamedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private Button startButton;

    @FXML
    void startAction(ActionEvent event) {
        Main.switchScene("battle.fxml");
    }

}
