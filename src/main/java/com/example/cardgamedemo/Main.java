package com.example.cardgamedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private static Stage mainStage;
    private static StackPane wrapper;
    private static final double BASE_WIDTH = 1920;
    private static double BASE_HEIGHT = 1080;
    @Override
    public void start(Stage stage) throws Exception{
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        wrapper = new StackPane(root);
        Scene scene = new Scene(wrapper, BASE_WIDTH, BASE_HEIGHT);
        bindScaling(root,scene);
        stage.setFullScreen(false);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setTitle("Ethan's Card Game");
        stage.setScene(scene);
        stage.show();
    }

    private static void bindScaling(Parent root, Scene scene) {
        root.scaleXProperty().bind(scene.widthProperty().divide(BASE_WIDTH));
        root.scaleYProperty().bind(scene.heightProperty().divide(BASE_HEIGHT));
    }

    public static void switchScene(String fxmlFile){
        try{
            Parent newRoot = FXMLLoader.load(Main.class.getResource(fxmlFile));
            Scene scene = mainStage.getScene();
            wrapper.getChildren().setAll(newRoot);
            bindScaling(newRoot,scene);
        } catch(IOException e){
            System.out.println("it broke idk");
        }
    }
    public static void main(String[] args){
        launch(args);
    }

}


