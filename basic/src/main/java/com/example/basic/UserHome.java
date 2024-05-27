package com.example.basic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class UserHome implements Initializable
{
    @FXML
    private Button addProduct_addBtn;

    @FXML
    private TextField addProduct_brandName;

    @FXML
    private TableView<productData> addProduct_tableView;

    @FXML
    private TableColumn<productData, String> addProduct_col_brandName;

    @FXML
    private TableColumn<productData, String> addProduct_col_description;

    @FXML
    private TableColumn<productData, String> addProduct_col_model;
    @FXML
    private Spinner<Integer> order_productQuantity;

    @FXML
    private TableColumn<productData, Integer> addProduct_col_price;

    @FXML
    private TableColumn<productData, String> addProduct_col_productType;

    @FXML
    private TableColumn<productData, Integer> addProduct_col_quantity;

    @FXML
    private Button addProduct_deleteBtn;

    @FXML
    private TextField addProduct_description;

    @FXML
    private AnchorPane addProduct_from;

    @FXML
    private Button cartBtn;

    @FXML
    private ImageView addProduct_imageView;

    @FXML
    private Button addProduct_importBtn;

    @FXML
    private TextField addProduct_type;

    @FXML
    private TextField addProduct_model;

    @FXML
    private TextField addProduct_price;

    @FXML
    private TextField addProduct_quantity;

    @FXML
    private Button addProduct_resetBtn;

    @FXML
    private TextField addProduct_search;


    @FXML
    private Button addProduct_updateBtn;

    @FXML
    private Button components_btn;

    @FXML
    private Button monitor_btn;

    @FXML
    private Button laptop_btn;

    @FXML
    private Button accesories_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    public  database data = new database();
    private PreparedStatement pst;
    private Statement stmt;
    private ResultSet result;
    private Image image;

    public ObservableList<productData>addLaptopProductsListData()
    {
        ObservableList<productData>productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product where producttype = 'Laptop'";
        data.connectDb();
        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();
            productData prodD;
            while(result.next())
            {
                prodD = new productData(
                        result.getString("productType"),
                        result.getString("productBrand"),
                        result.getString("productModel"),
                        result.getDouble("productPrice"),
                        result.getInt("productQuantity"),
                        result.getString("productDescription"),
                        result.getString("productImage"));
                productList.add(prodD);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;
    }

    public ObservableList<productData>addMonitorProductsListData()
    {
        ObservableList<productData>productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product where producttype = 'Monitor'";
        data.connectDb();
        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();
            productData prodD;
            while(result.next())
            {
                prodD = new productData(
                        result.getString("productType"),
                        result.getString("productBrand"),
                        result.getString("productModel"),
                        result.getDouble("productPrice"),
                        result.getInt("productQuantity"),
                        result.getString("productDescription"),
                        result.getString("productImage"));
                productList.add(prodD);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;
    }

    public ObservableList<productData>addAccessoriesProductsListData()
    {
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product where producttype = 'Accessories'";
        data.connectDb();
        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();
            productData prodD;
            while(result.next())
            {
                prodD = new productData(
                        result.getString("productType"),
                        result.getString("productBrand"),
                        result.getString("productModel"),
                        result.getDouble("productPrice"),
                        result.getInt("productQuantity"),
                        result.getString("productDescription"),
                        result.getString("productImage"));
                productList.add(prodD);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;
    }

    public ObservableList<productData>addComponentsProductsListData()
    {
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product where producttype = 'Components'";
        data.connectDb();
        try
        {
            pst = data.conn.prepareStatement(sql);
            result = pst.executeQuery();
            productData prodD;
            while(result.next())
            {
                prodD = new productData(
                        result.getString("productType"),
                        result.getString("productBrand"),
                        result.getString("productModel"),
                        result.getDouble("productPrice"),
                        result.getInt("productQuantity"),
                        result.getString("productDescription"),
                        result.getString("productImage"));
                productList.add(prodD);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;
    }

    private ObservableList<productData>addLaptopProductList;
    private ObservableList<productData>addMonitorProductList;
    private ObservableList<productData>addAccessoriesProductList;
    private ObservableList<productData>addComponentsProductList;
    public void addLaptopProductsShowData()
    {
        addLaptopProductList = addLaptopProductsListData();

        addProduct_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addProduct_col_brandName.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        addProduct_col_model.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        addProduct_col_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        addProduct_col_description.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        addProduct_tableView.setItems(addLaptopProductList);
    }
    public void addMonitorProductsShowData()
    {
        addMonitorProductList = addMonitorProductsListData();

        addProduct_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addProduct_col_brandName.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        addProduct_col_model.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        addProduct_col_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        addProduct_col_description.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        addProduct_tableView.setItems(addMonitorProductList);
    }

    public void addAccessoriesProductsShowData()
    {
        addAccessoriesProductList = addAccessoriesProductsListData();

        addProduct_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addProduct_col_brandName.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        addProduct_col_model.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        addProduct_col_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        addProduct_col_description.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        addProduct_tableView.setItems(addAccessoriesProductList);
    }

    public void addComponentsProductsShowData()
    {
        addComponentsProductList = addComponentsProductsListData();

        addProduct_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addProduct_col_brandName.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        addProduct_col_model.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        addProduct_col_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        addProduct_col_description.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        addProduct_tableView.setItems(addComponentsProductList);
    }

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        if(event.getSource() == laptop_btn)
        {
            addLaptopProductsShowData();
            addProduct_tableView.refresh();
            laptop_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            accesories_btn.setStyle("-fx-background-color: transparent");
            monitor_btn.setStyle("-fx-background-color: transparent");
            components_btn.setStyle("-fx-background-color: transparent");
            FilteredList<productData>filteredData = new FilteredList<>(addLaptopProductList,e->true);
            addProduct_search.setOnKeyTyped(e-> {
                addProduct_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super productData>) laptop -> {
                        if (newValue == null || newValue.isEmpty())
                            return true;
                        String lowercasefilter=newValue.toLowerCase();
                        if(laptop.getProductModel().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductBrand().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductType().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductDescription().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductPrice().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductQuantity().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else
                            return false;

                    });
                });
                SortedList<productData>sortlist=new SortedList<>(filteredData);
                sortlist.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
                addProduct_tableView.setItems(sortlist);
            });
        }
        else if(event.getSource() == accesories_btn)
        {
            System.out.println("ASSDADAAD");
            addAccessoriesProductsShowData();
            addProduct_tableView.refresh();
            accesories_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            laptop_btn.setStyle("-fx-background-color: transparent");
            monitor_btn.setStyle("-fx-background-color: transparent");
            components_btn.setStyle("-fx-background-color: transparent");
            FilteredList<productData>filteredData = new FilteredList<>(addAccessoriesProductList,e->true);
            addProduct_search.setOnKeyTyped(e-> {
                addProduct_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super productData>) accesories -> {
                        if (newValue == null || newValue.isEmpty())
                            return true;
                        String lowercasefilter=newValue.toLowerCase();
                        if(accesories .getProductModel().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(accesories .getProductBrand().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(accesories .getProductType().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(accesories .getProductDescription().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(accesories .getProductPrice().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(accesories .getProductQuantity().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else
                            return false;

                    });

                });
                SortedList<productData>sortlist=new SortedList<>(filteredData);
                sortlist.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
                addProduct_tableView.setItems(sortlist);
            });
        }
        else if(event.getSource() == components_btn)
        {
            addComponentsProductsShowData();
            addProduct_tableView.refresh();
            components_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            accesories_btn.setStyle("-fx-background-color: transparent");
            monitor_btn.setStyle("-fx-background-color: transparent");
            laptop_btn.setStyle("-fx-background-color: transparent");

            FilteredList<productData>filteredData = new FilteredList<>(addComponentsProductList,e->true);
            addProduct_search.setOnKeyTyped(e-> {
                addProduct_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super productData>) laptop -> {
                        if (newValue == null || newValue.isEmpty())
                            return true;
                        String lowercasefilter=newValue.toLowerCase();
                        if(laptop.getProductModel().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductBrand().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductType().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductDescription().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductPrice().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(laptop.getProductQuantity().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else
                            return false;

                    });
                });
                SortedList<productData>sortlist=new SortedList<>(filteredData);
                sortlist.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
                addProduct_tableView.setItems(sortlist);
            });
        }
        else if(event.getSource() == monitor_btn)
        {
            addMonitorProductsShowData();
            addProduct_tableView.refresh();
//            System.out.println("ok boss");
            monitor_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            accesories_btn.setStyle("-fx-background-color: transparent");
            laptop_btn.setStyle("-fx-background-color: transparent");
            components_btn.setStyle("-fx-background-color: transparent");
            FilteredList<productData>filteredData = new FilteredList<>(addMonitorProductList,e->true);
            addProduct_search.setOnKeyTyped(e-> {
                addProduct_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super productData>) monitor -> {
                        if (newValue == null || newValue.isEmpty())
                            return true;
                        String lowercasefilter=newValue.toLowerCase();
                        if(monitor.getProductModel().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(monitor.getProductBrand().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(monitor.getProductType().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(monitor.getProductDescription().toLowerCase().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(monitor.getProductPrice().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else if(monitor.getProductQuantity().toString().indexOf(lowercasefilter)>-1)
                        {
                            return true;
                        }
                        else
                            return false;

                    });

                });
                SortedList<productData>sortlist=new SortedList<>(filteredData);
                sortlist.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
                addProduct_tableView.setItems(sortlist);
            });
        }
        else if(event.getSource() == addProduct_resetBtn)
        {
            addProductReset();
        }
        else if(event.getSource() == cartBtn)
        {
            System.out.println("ttttttttttt");
            root= FXMLLoader. load(this.getClass().getResource("order.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            root.setOnMousePressed((MouseEvent event1)->{
                x=event1.getScreenX();
                y=event1.getScreenY();
            });
            root.setOnMouseDragged((MouseEvent event1)->{
                stage.setX(event1.getScreenX()-x);
                stage.setY(event1.getScreenY()-y);
                stage.setOpacity(.8);
            });
            root.setOnMouseReleased((MouseEvent event1)->{
                stage.setOpacity(1);
            });

            stage.setScene((scene));
            stage.show();
        }

    }

    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
        root= FXMLLoader.load(this.getClass().getResource("Userlogin.fxml"));
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


    @FXML
    public void addProductSelect() {

        productData prodD = addProduct_tableView.getSelectionModel().getSelectedItem();
        int num = addProduct_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1) return;

        System.out.println(num);

        addProduct_type.setText(prodD.getProductType());
        addProduct_brandName.setText(prodD.getProductBrand());
        addProduct_model.setText(prodD.getProductModel());
        addProduct_price.setText(Double.toString(prodD.getProductPrice()));
        addProduct_description.setText(prodD.getProductDescription());

        String uri = "file:" + prodD.getProductImage();
        image = new Image(uri, 147, 137, false, true);
        addProduct_imageView.setImage(image);
    }
    private SpinnerValueFactory<Integer> spinner;
    public void ordersSpinner()
    {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        order_productQuantity.setValueFactory(spinner);

        ordersShowSpinnerValue();
    }
    private int qty;
    public void ordersShowSpinnerValue()
    {
        qty = order_productQuantity.getValue();
    }

    public void addProductAdd()
    {
        String insertProduct="Insert into customer "
                +"values(?,?,?,?,?,?,?,?)";

        data.connectDb();
        try
        {
            Alert alert;
            if(order_productQuantity.getValue()==null)
            {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill up your required quantity");
                alert.showAndWait();
            }

            String getquantity="Select * from product where productmodel = '"+addProduct_model.getText()+"' and producttype='"
                    +addProduct_type.getText()+"'";

            stmt=data.conn.createStatement();
            ResultSet rs1=stmt.executeQuery(getquantity);

            String check = "SELECT EXISTS(SELECT 1 FROM customer WHERE productmodel = '"+addProduct_model.getText()+"' and username = '"+getData.customerusername+"' and payment = '"+"Undone"+"')";
            stmt = data.conn.createStatement();
            ResultSet rs2 = stmt.executeQuery(check);
            Boolean chk=false;
            while(rs2.next())
            {
                chk = rs2.getBoolean("exists");
            }
            int have=0;
            while(rs1.next())
            {
                have= rs1.getInt("productquantity");
            }
            if(have<qty)
            {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("We don't have enough product of your choosing type");
            }
            else if(chk)
            {
                String fetch = "Select quantity from customer where productmodel = '"+addProduct_model.getText()+"' and username = '"+getData.customerusername+"' and payment = '"+"Undone"+"'";
                stmt = data.conn.createStatement();
                rs2 = stmt.executeQuery(fetch);
                while(rs2.next())
                {
                    qty += rs2.getInt("quantity");
                }
                if(have<qty)
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("We don't have enough product of your choosing type");
                    alert.showAndWait();
                }
                else
                {
                    check = "update customer set quantity = '"+String.valueOf(qty)+"' WHERE productmodel = '"+addProduct_model.getText()+"' and username = '"+getData.customerusername+"' and payment = '"+"Undone"+"'";
                    pst = data.conn.prepareStatement(check);
                    pst.executeUpdate();
                }
            }
            else
            {
                pst = data.conn.prepareStatement(insertProduct);
                pst.setString(1, addProduct_type.getText());
                pst.setString(2, addProduct_brandName.getText());
                pst.setString(3, addProduct_model.getText());
                pst.setString(4, String.valueOf(qty));
                pst.setDouble(5, Double.parseDouble(addProduct_price.getText()));
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                pst.setDate(6,Date.valueOf(sqlDate.toLocalDate()));
                pst.setString(7, getData.customerusername);
                pst.setString(8, String.valueOf("Undone"));
                pst.executeUpdate();

                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added to the cart");
                alert.showAndWait();
            }
            addProductReset();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addProductImportImage()
    {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        ExtensionFilter imageFile = new ExtensionFilter("Image File","*jpg","*png");
        open.getExtensionFilters().add(imageFile);
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        if(file != null)
        {
            getData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 147, 137, false, true);
            addProduct_imageView.setImage(image);
        }
    }

    public void addProductReset()
    {
        addProduct_type.setText("");
        addProduct_brandName.setText("");
        addProduct_model.setText("");
        addProduct_price.setText("");
        addProduct_description.setText("");
        addProduct_imageView.setImage(null);
        getData.path = "";
        ordersSpinner();
    }




    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        addLaptopProductsShowData();
        addProduct_tableView.refresh();
        laptop_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
        accesories_btn.setStyle("-fx-background-color: transparent");
        monitor_btn.setStyle("-fx-background-color: transparent");
        components_btn.setStyle("-fx-background-color: transparent");
        FilteredList<productData>filteredData = new FilteredList<>(addLaptopProductList,e->true);
        addProduct_search.setOnKeyTyped(e-> {
            addProduct_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super productData>) laptop -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;
                    String lowercasefilter=newValue.toLowerCase();
                    if(laptop.getProductModel().toLowerCase().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else if(laptop.getProductBrand().toLowerCase().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else if(laptop.getProductType().toLowerCase().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else if(laptop.getProductDescription().toLowerCase().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else if(laptop.getProductPrice().toString().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else if(laptop.getProductQuantity().toString().indexOf(lowercasefilter)>-1)
                    {
                        return true;
                    }
                    else
                        return false;

                });
            });
            SortedList<productData>sortlist=new SortedList<>(filteredData);
            sortlist.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
            addProduct_tableView.setItems(sortlist);
        });
        ordersSpinner();
        laptop_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
        accesories_btn.setStyle("-fx-background-color: transparent");
        monitor_btn.setStyle("-fx-background-color: transparent");
        components_btn.setStyle("-fx-background-color: transparent");
        //addLaptopProductSearch();
    }
}
