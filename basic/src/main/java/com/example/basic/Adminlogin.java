package com.example.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adminlogin
{
    @FXML
    Button login,signup,backButton;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    Alert aLert;
    @FXML
    public void LoginPress(ActionEvent e) throws InterruptedException, SQLException, IOException {
        String name=this.username.getText();
        String pass=this.password.getText();
        Integer count=0;

        if(name.length()==0&&pass.length()==0)
        {
            //empty.setText("Username and password are empty");
            aLert=new Alert(Alert.AlertType.ERROR);
            aLert.setTitle("Error Message");
            aLert.setHeaderText(null);
            aLert.setContentText("Please fill up all the fields");
            aLert.showAndWait();
        }
        else if(name.length()==0)
        {
            System.out.println("AA");
            aLert=new Alert(Alert.AlertType.ERROR);
            aLert.setTitle("Error Message");
            aLert.setHeaderText(null);
            aLert.setContentText("Please fill Username");
            aLert.showAndWait();
        }
        else if(pass.length()==0)
        {
            aLert=new Alert(Alert.AlertType.ERROR);
            aLert.setTitle("Error Message");
            aLert.setHeaderText(null);
            aLert.setContentText("Please fill up password");
            aLert.showAndWait();
        }
        else
        {
            database c=new database();
            c.connectDb();
            int i = 4;
            if(c.conn.isValid(i))
                System.out.println("ok");
            if(c.conn.isClosed());
            System.out.println("not ok");
            Statement stmt=c.conn.createStatement() ;
            ResultSet result=stmt.executeQuery("Select * from admin");
            while(result.next())
            {
                System.out.println(count);
                count++;
                String checkusername=result.getString("username");
                String checkpass=result.getString("password");
                if(name.equals(checkusername)&&pass.equals(checkpass))
                {
                    getData.username=checkusername;
                    root= FXMLLoader.load(this.getClass().getResource("AdminHome.fxml"));
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
                    break;

                }
                else
                {

                    System.out.println("ADSAD "+ count);
                    //empty.setText("Username or password is wrong");
                    aLert=new Alert(Alert.AlertType.ERROR);
                    aLert.setTitle("Error Message");
                    aLert.setHeaderText(null);
                    aLert.setContentText("Wrong username or password");
                    aLert.showAndWait();
                }

            }
        }

    }

    private double x= 0;
    private double y = 0;
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
        root= FXMLLoader.load(this.getClass().getResource("Adminsignup.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene((scene));
        stage.show();
    }
}
