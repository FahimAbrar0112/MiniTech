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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Sell implements Initializable
{
    @FXML
    private Button laptop_btn;
    @FXML
    private Button monitor_btn;
    @FXML
    private Button components_btn;
    @FXML
    private Button accesories_btn;
    @FXML
    private Button order_addBtn;
    @FXML
    private TextField order_amount;
    @FXML
    private Label order_balance;
    @FXML
    private TableView<sellData> order_tableView;
    @FXML
    private TableColumn<sellData, Integer> order_col_Quantity;
    @FXML
    private TableColumn<sellData, String> order_col_productBrand;
    @FXML
    private TableColumn<sellData, String> order_col_productModel;
    @FXML
    private TableColumn<sellData, Double> order_col_productPrice;
    @FXML
    private TableColumn<sellData, String> order_col_productType;
    @FXML
    private TableColumn<sellData, Date> order_col_productDate;
    @FXML
    private TableColumn<sellData, Double> order_col_productTotal;
    @FXML
    private Button order_payBtn;
    @FXML
    private ComboBox<?> order_productBrand;
    @FXML
    private ComboBox<?> order_productModel;
    @FXML
    private Spinner<Integer> order_productQuantity;
    @FXML
    private ComboBox<?> order_productType;
    @FXML
    private Button order_receiptBtn;
    @FXML
    private Button order_resetBtn;
    @FXML
    private Button order_deleteBtn;
    @FXML
    private Label order_total;
    @FXML
    private AnchorPane orders_form;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    private Connection connect;
    public  database data = new database();
    private PreparedStatement pst;
    private Statement stmt;
    private ResultSet result;
    private Image image;


    public ObservableList<sellData>ordersListData()
    {
        ObservableList<sellData>listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE payment = 'Done'";

        data.connectDb();
        try
        {
            stmt = data.conn.createStatement();
            result = stmt.executeQuery(sql);

            while(result.next())
            {
                int quantity = Integer.parseInt(result.getString("quantity"));
                double price = result.getDouble("price");
                sellData sellD = new sellData(
                        result.getString("producttype"),
                        result.getString("productbrand"),
                        result.getString("productmodel"),
                        quantity,
                        price,
                        quantity*price,
                        result.getDate("date"));
                listData.add(sellD);
                System.out.println(quantity+" "+price);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return listData;
    }

    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
        root= FXMLLoader.load(this.getClass().getResource("AdminHome.fxml"));
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

    private double totalP = 0;
    public void ordersDisplayTotal()
    {
        String sql = "SELECT price,quantity FROM customer WHERE  payment = 'Done'";
        data.connectDb();
        try{
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();
            double sum = 0;
            while(result.next())
            {
                sum += result.getDouble("price")*Integer.parseInt(result.getString("quantity"));
            }
            totalP = sum;
            order_total.setText("$"+String.valueOf(totalP));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private ObservableList<sellData> ordersList;
    private void ordersShowListData()
    {
        ordersList = ordersListData();

        order_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        order_col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        order_col_productModel.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        order_col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        order_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        order_col_productTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        order_col_productDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        order_tableView.setItems(ordersList);

//        ordersDisplayTotal();
    }
    public void saleratiopress(ActionEvent e) throws IOException {
        root= FXMLLoader.load(this.getClass().getResource("sellratio.fxml"));
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


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        System.out.println("ok boss");
        ordersShowListData();
        ordersDisplayTotal();
    }
    public void onHelloButtonClick(ActionEvent event) {
    }
}
