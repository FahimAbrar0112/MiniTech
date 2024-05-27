package com.example.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    @FXML
    Button User;
    @FXML
    Button Admin;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double x=0;
    private double y=0;
    public void AdminPress(ActionEvent e) throws IOException {
         root=FXMLLoader.load(this.getClass().getResource("Adminlogin.fxml"));
         stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

         scene=new Scene(root);
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
         stage.setScene((scene));
         stage.show();

    }
    public void UserPress(ActionEvent e) throws IOException {
        root=FXMLLoader.load(this.getClass().getResource("Userlogin.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene((scene));
        stage.show();

    }


}