package com.example.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    private  double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root,1280,720);
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getScreenX();
            y=event.getScreenY();
        });
        root.setOnMouseDragged((MouseEvent event)->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
            stage.setOpacity(.8);
        });
        root.setOnMouseReleased((MouseEvent event)->{
            stage.setOpacity(1);
        });

        stage.setTitle("MINITECH");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}