package com.example.basic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class sellratio implements Initializable {
    Statement stmt;
    @FXML
    private PieChart sell_ratio_chart=new PieChart();
    @FXML
    Button color;
    public void piechartshow() throws SQLException {
        String sql1="Select quantity from customer where producttype='Laptop' and payment='Done'";
        String sql2="Select quantity from customer where producttype='Monitor' and payment='Done'";
        String sql3="Select quantity from customer where producttype='Accessories' and payment='Done'";
        String sql4="Select quantity from customer where producttype='Components' and payment='Done'";
        database c=new database();
        c.connectDb();
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        stmt=c.conn.createStatement();
        ResultSet rs1=stmt.executeQuery(sql1);
        while(rs1.next())
        {
            //System.out.println("AAA");
            count1+=Integer.parseInt(rs1.getString("quantity"));
        }
        ResultSet rs2=stmt.executeQuery(sql2);
        while(rs2.next())
        {
            count2+=Integer.parseInt(rs2.getString("quantity"));
        }
        ResultSet rs3=stmt.executeQuery(sql3);
        while(rs3.next())
        {
            count3+=Integer.parseInt(rs3.getString("quantity"));
        }
        ResultSet rs4=stmt.executeQuery(sql4);
        while(rs4.next())
        {
            count4+=Integer.parseInt(rs4.getString("quantity"));
        }
        System.out.println(count1+" "+count2+" "+count3+" "+count4);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Laptop", count1),
                new PieChart.Data("Monitor", count2),
                new PieChart.Data("Accessories", count3),
                new PieChart.Data("Components", count4));

        sell_ratio_chart.setData(pieChartData);
        sell_ratio_chart.setClockwise(true);
        sell_ratio_chart.setLabelsVisible(true);
        sell_ratio_chart.setTitle("Products Counts");
        sell_ratio_chart.setLabelsVisible(true);
        sell_ratio_chart.setStartAngle(180);
    }
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
        root= FXMLLoader.load(this.getClass().getResource("sell.fxml"));
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
    public void colorpress() throws SQLException {
        piechartshow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            piechartshow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
