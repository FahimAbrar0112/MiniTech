package com.example.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Userlogin {
    @FXML
    Button login,signup;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label empty;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    public void LoginPress(ActionEvent e) throws InterruptedException, SQLException, IOException {
        String name=this.username.getText();
        String pass=this.password.getText();

        if(name.length()==0&&pass.length()==0)
        {
            empty.setText("Username and password are empty");

        }
        else if(name.length()==0)
        {
            empty.setText("Username is empty");
        }
        else if(pass.length()==0)
        {
            empty.setText("Password is empty");
        }
        else
        {
            database c=new database();
            c.connectDb();
            Statement stmt=c.conn.createStatement() ;
            ResultSet result=stmt.executeQuery("Select * from miniuser");
            while(result.next())
            {
                String checkusername=result.getString("username");
                String checkpass=result.getString("password");
                if(name.equals(checkusername)&&pass.equals(checkpass))
                {
                    getData.customerusername=checkusername;
                    root= FXMLLoader.load(this.getClass().getResource("UserHome.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene=new Scene(root);
                    stage.setScene((scene));
                    stage.show();
                }
                else
                {
                    this.empty.setText("Username or password is wrong");
                }

            }
        }

    }

    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
        root= FXMLLoader.load(this.getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        root.setOnMousePressed((javafx.scene.input.MouseEvent event)->{
            x=event.getScreenX();
            y=event.getScreenY();
        });
        root.setOnMouseDragged((javafx.scene.input.MouseEvent event)->{
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

    public void SignupPress(ActionEvent e) throws IOException {
        root= FXMLLoader.load(this.getClass().getResource("UserSignup.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene((scene));
        stage.show();
    }
}
