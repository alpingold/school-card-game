package com.example.cardgamedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception{
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Scene scene = new Scene(root);
        stage.setMinHeight(1024);
        stage.setMinWidth(1024);
        stage.setResizable(false);
        stage.setTitle("Ethan's Card Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchScene(String fxmlFile){
        try{
            Parent newRoot = FXMLLoader.load(Main.class.getResource(fxmlFile));
            mainStage.getScene().setRoot(newRoot);
        } catch(IOException e){
            System.out.println("it broke idk");
        }
    }
    public static void main(String[] args){
        launch(args);
    }

}


