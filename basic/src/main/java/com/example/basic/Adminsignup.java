package com.example.basic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adminsignup{
    @FXML
    TextField name,username,password,age,gender;
    @FXML
    Button signup;
    @FXML
    Label empty;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    public void SignupPress(ActionEvent e) throws SQLException {
        String name1=name.getText();
        String username1=username.getText();
        String password1=password.getText();

        String gender1=gender.getText();
        if(name1.length()==0||username1.length()==0||password1.length()==0||age.getText().length()==0||gender1.length()==0)
        {
            empty.setText("Fillup all the field");
        }
        else if(password1.length()<6)
        {
            empty.setText("Password size should be more than six");
        }
        else
        {
            int age1=Integer.parseInt(age.getText());
            database c=new database();
            c.connectDb();
            Statement stmt=c.conn.createStatement() ;
            ResultSet result=stmt.executeQuery("Select * from admin");
            Boolean answer=false;
            while(result.next())
            {
                String checkusername=result.getString("username");
                if(username1.equals(checkusername))
                {
                    answer=true;
                    empty.setText("Username already exist");
                    break;
                }
            }
            if(!answer)
            {
                System.out.println("AAAA");
                try {
                    PreparedStatement pstmt = c.conn.prepareStatement("insert into admin values (?,?,?,?,?)");
                    pstmt.setString(1, name1);
                    pstmt.setString(2, username1);
                    pstmt.setString(3, password1);
                    pstmt.setInt(4, age1);
                    pstmt.setString(5, gender1);
                    pstmt.executeUpdate();
                    root= FXMLLoader.load(this.getClass().getResource("AdminHome.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene=new Scene(root);
                    stage.setScene((scene));
                    stage.show();

                } catch (SQLException var6) {
                    this.empty.setText("There is someone else with the same username.Try again.");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }

    }
}
