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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class userOrder implements Initializable
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
    private TableView<customerData> order_tableView;
    @FXML
    private TableColumn<customerData, Integer> order_col_Quantity;
    @FXML
    private TableColumn<customerData, String> order_col_productBrand;
    @FXML
    private TableColumn<customerData, String> order_col_productModel;
    @FXML
    private TableColumn<customerData, Double> order_col_productPrice;
    @FXML
    private TableColumn<customerData, String> order_col_productType;
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



    private SpinnerValueFactory<Integer> spinner;
    public void ordersSpinner()
    {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        order_productQuantity.setValueFactory(spinner);
    }
    private int qty;
    public void ordersShowSpinnerValue()
    {
        qty = order_productQuantity.getValue();
    }
    public ObservableList<customerData>ordersListData()
    {
        ObservableList<customerData>listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE username = '"+getData.customerusername+"' and payment = '"+"Undone"+"'";

        data.connectDb();
        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();

            while(result.next())
            {
                customerData customerD = new customerData(
                        result.getString("producttype"),
                        result.getString("productbrand"),
                        result.getString("productmodel"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getDate("date"));
                listData.add(customerD);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return listData;
    }
//    public void ordersAdd()
//    {
//        String sql = "INSERT INTO customer (customerid,producttype,productbrand,productmodel,quantity,price,date)"
//                + "VALUES(?,?,?,?,?,?,?)";
//        data.connectDb();
//        try{
//
//            String checkData = "SELECT * FROM product WHERE productModel= '"
//                    +order_productModel.getSelectionModel().getSelectedItem()+"'";
//            double priceData = 0;
//            stmt = data.conn.createStatement();
//            result = stmt.executeQuery(checkData);
//            if(result.next())
//                priceData = result.getDouble("productprice");
//            double totalPData = priceData*qty;
//
//            Alert alert;
//
//            if(order_productType.getSelectionModel().getSelectedItem() == null
//                    ||order_productBrand.getSelectionModel().getSelectedItem() == null
//                    ||order_productModel.getSelectionModel().getSelectedItem() == null
//                    ||totalPData == 0)
//            {
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please choose product first");
//                alert.showAndWait();
//            }
//            else
//            {
//                pst = data.conn.prepareStatement(sql);
//                pst.setString(2,(String)order_productType.getSelectionModel().getSelectedItem());
//                pst.setString(3,(String)order_productBrand.getSelectionModel().getSelectedItem());
//                pst.setString(4,(String)order_productModel.getSelectionModel().getSelectedItem());
//                pst.setString(5,String.valueOf(qty));
//                pst.setDouble(6,Double.valueOf(totalPData));
//
//                Date date = new Date(System.currentTimeMillis());
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                pst.setDate(7,Date.valueOf(sqlDate.toLocalDate()));
//
//                pst.executeUpdate();
//
//                ordersShowListData();
//                ordersDisplayTotal();
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
        root= FXMLLoader.load(this.getClass().getResource("Userhome.fxml"));
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

    public void orderDelete()
    {
        customerData prodD = order_tableView.getSelectionModel().getSelectedItem();
        int num = order_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1) return;

        System.out.println(num);

        String sql = "Delete from customer where productmodel = '"+prodD.getProductModel()+"' and payment = '"+"Undone"+"' and username = '"+getData.customerusername+"'";

        data.connectDb();
        try
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to reset?");
            Optional<ButtonType>option = alert.showAndWait();

            stmt = data.conn.createStatement();
            stmt.executeUpdate(sql);

            ordersShowListData();
            ordersDisplayTotal();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ordersReset()
    {
        String sql = "DELETE FROM customer WHERE username = '"+getData.customerusername+"' and payment = '"+"Undone"+"'";
        data.connectDb();

        try
        {
            if(!order_tableView.getItems().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset?");
                Optional<ButtonType>option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    stmt = data.conn.createStatement();
//                    stmt.executeUpdate(sql);
                }

                ordersShowListData();

                totalP = 0;
                balanceP = 0;
                amountP = 0;

                order_balance.setText("$0.0");
                order_total.setText("$0.0");
                order_amount.setText("");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private double amountP;
    private double balanceP;
    public  void ordersAmount()
    {
        Alert alert;

        if(!order_amount.getText().isEmpty())
        {
            amountP = Double.parseDouble(order_amount.getText());

            if(totalP>0)
            {
                if(amountP >= totalP)
                {
                    balanceP = (amountP-totalP);
                    order_balance.setText("$"+String.valueOf(balanceP));
                }
                else
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid :3");
                    alert.showAndWait();

                    order_amount.setText("");
                }
            }
            else
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }
        }
        else
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        }
    }

    private double totalP = 0;
    public void ordersDisplayTotal()
    {
        String sql = "SELECT price,quantity FROM customer WHERE username = '"+ getData.customerusername+"' and payment = '"+"Undone"+"'";
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

    public void orderReceipt() throws SQLException, FileNotFoundException {
        receipt r = new receipt();
        r.create();
    }

    public void ordersPay()
    {
        String sql = "INSERT INTO customer_receipt (total,date,amount,balance,username)"
                +"VALUES(?,?,?,?,?)";

        data.connectDb();
        try
        {
            Alert alert;
            ordersAmount();
            System.out.println(amountP);
            System.out.println(balanceP);
            if(totalP>0 && !order_amount.getText().isEmpty() && amountP!=0 && (totalP<amountP || totalP==amountP) )
            {
                balanceP = amountP - totalP;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType>option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK))
                {
                    pst = data.conn.prepareStatement(sql);
                    pst.setDouble(1,Double.valueOf(totalP));
                    Date date = new Date(System.currentTimeMillis());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    pst.setDate(2,Date.valueOf(sqlDate.toLocalDate()));
                    pst.setDouble(3,Double.valueOf(amountP));
                    pst.setDouble(4,Double.valueOf(balanceP));
                    pst.setString(5,getData.customerusername);
                    pst.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful");
                    alert.showAndWait();

                    sql = "select productmodel,quantity from customer where username = '"+getData.customerusername+"' and payment = 'Undone'";
                    stmt = data.conn.createStatement();
                    stmt.executeQuery(sql);
                    result = stmt.getResultSet();

                    while(result.next())
                    {
                        String sql2 = "update product set productquantity = productquantity - "+Integer.parseInt(result.getString("quantity"))+" where product.productmodel = '"+result.getString("productmodel")+"'";
                        pst = data.conn.prepareStatement(sql2);
                        pst.executeUpdate();
                    }

                    receipt r = new receipt();
                    r.create();

                    sql = "update customer set payment = 'Done' where username = '"+getData.customerusername+"' and payment = '"+"Undone"+"'";
                    pst = data.conn.prepareStatement(sql);
                    pst.executeUpdate();

                    totalP = 0;
                    amountP = 0;
                    order_amount.setText("");

                    ordersDisplayTotal();
                    ordersShowListData();
                    ordersDisplayBalance();

                }
                else return;

            }
            else
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String[] orderListType = {"Laptop", "Monitor", "Accessories", "Components"};
    public void ordersListType()
    {
        List<String> listT = new ArrayList<>();

        for(String data : orderListType)
        {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        order_productType.setItems(listData);
        ordersListBrand();
    }

    public void ordersListBrand()
    {
        String sql = "SELECT productbrand FROM product WHERE producttype = '"
                +order_productType.getSelectionModel().getSelectedItem()
                +"' and productquantity>0";

        data.connectDb();

        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();
            while(result.next())
            {
                listData.add(result.getString("productbrand"));
            }
            order_productBrand.setItems(listData);
            ordersListProductModel();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void ordersListProductModel()
    {
        String sql = "SELECT productmodel FROM product WHERE productbrand = '"
                +order_productBrand.getSelectionModel().getSelectedItem()+"'";

        data.connectDb();

        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();
            while(result.next())
            {
                listData.add(result.getString("productmodel"));
            }
            order_productModel.setItems(listData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ordersDisplayBalance()
    {
        String sql = "SELECT SUM(balance) FROM customer_receipt WHERE username = '"+ getData.customerusername+"' and balance>0";
        data.connectDb();
        try{
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();

            while(result.next())
            {
                balanceP = result.getDouble("SUM");
            }
            order_balance.setText("$"+String.valueOf(balanceP));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private ObservableList<customerData> ordersList;
    private void ordersShowListData()
    {
        ordersList = ordersListData();

        order_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        order_col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        order_col_productModel.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        order_col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        order_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        order_tableView.setItems(ordersList);

        ordersDisplayTotal();
    }


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
//        ordersListType();
        ordersShowListData();
//        ordersListBrand();
//        ordersListProductModel();
//        ordersSpinner();
        ordersDisplayTotal();
        ordersDisplayBalance();

    }

    public void onHelloButtonClick(ActionEvent event) {
    }
}
