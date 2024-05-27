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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AdminProduct implements Initializable
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
        String sql = "SELECT * FROM product where producttype = 'Accessories' and productquantity>0";
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
        addProduct_col_quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
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
        addProduct_col_quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
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
        addProduct_col_quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
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
        addProduct_col_quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        addProduct_col_description.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        addProduct_tableView.setItems(addComponentsProductList);
    }

    @FXML
    protected void onHelloButtonClick(ActionEvent event)
    {
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
        else if(event.getSource() == addProduct_importBtn)
        {
            addProductImportImage();
        }
        else if(event.getSource() == addProduct_resetBtn)
        {
            addProductReset();
        }
        else if(event.getSource()==addProduct_updateBtn)
        {
            addProductUpdate();
        }
        else if(event.getSource()==addProduct_deleteBtn)
        {
            addProductDelete();
        }
    }

    private double x=0;
    private double y=0;
    @FXML
    protected void onClickBack(ActionEvent e) throws IOException
    {
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
        addProduct_quantity.setText(Integer.toString(prodD.getProductQuantity()));
        addProduct_description.setText(prodD.getProductDescription());
        String uri = prodD.getProductImage();
        image = new Image(uri, 147, 137, false, true);
        getData.path = uri;
        addProduct_imageView.setImage(image);
    }

    public void addProductAdd()
    {
        String insertProduct="Insert into product "
                            +"values(?,?,?,?,?,?,?)";

        data.connectDb();
        try
        {
            Alert alert;

            if(     addProduct_model.getText().isEmpty()||
                    addProduct_brandName.getText().isEmpty()||
                    addProduct_type.getText().isEmpty()||
                    addProduct_price.getText().isEmpty()||
                    addProduct_description.getText().isEmpty()||
                    addProduct_quantity.getText().isEmpty() ||
                    getData.path == null
            )
            {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else
            {
                String check="Select * from product where productmodel='"+addProduct_model.getText()+"' and producttype='"+ addProduct_type.getText()+"'";
                stmt = data.conn.createStatement();
                result=stmt.executeQuery(check);
                Boolean answer=false;
                if(result.next())
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product model with the same product type is already existed");
                    alert.showAndWait();

                }
                else
                {
                    pst = data.conn.prepareStatement(insertProduct);
                    pst.setString(1, addProduct_type.getText());
                    pst.setString(2, addProduct_brandName.getText());
                    pst.setString(3, addProduct_model.getText());
                    pst.setDouble(4, Double.parseDouble(addProduct_price.getText()));
                    pst.setInt(5, Integer.parseInt(addProduct_quantity.getText()));
                    pst.setString(6, addProduct_description.getText());
                    String uri = getData.path;
                    uri = uri.replace("\\","\\\\");
                    pst.setString(7, uri);

                    pst.executeUpdate();

                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    if(addProduct_type.getText().equals("Laptop"))
                    {
                        addLaptopProductsShowData();
                    }
                    else if(addProduct_type.getText().equals("Monitor"))
                    {
                        addMonitorProductsShowData();
                    }
                    else if(addProduct_type.getText().equals("Accessories"))
                    {
                        addAccessoriesProductsShowData();
                    }
                    else if(addProduct_type.getText().equals("Components"))
                    {
                        addComponentsProductsShowData();
                    }

                    addProductReset();
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
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
            System.out.println(getData.path);
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
        addProduct_quantity.setText("");
        addProduct_description.setText("");
        addProduct_imageView.setImage(null);
        getData.path = "";
    }

    public void addProductUpdate()
    {
        database c=new database();
        c.connectDb();
        try
        {
            Alert alert;

            if(addProduct_model.getText().isEmpty()||
                    addProduct_brandName.getText().isEmpty()||
                    addProduct_type.getText().isEmpty()||
                    addProduct_price.getText().isEmpty()||addProduct_description.getText().isEmpty()
                    ||addProduct_quantity.getText().isEmpty()
                    ||getData.path == null
                    ) {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else
            {
                String uri = getData.path;
                System.out.println(uri);
                uri = uri.replace("\\","\\\\");
                String sql="Update product set "+
                        "productbrand= '"+addProduct_brandName.getText()+"',"+
                        "producttype='"+addProduct_type.getText()+"',"+
                        "productdescription='"+addProduct_description.getText()+"',"+
                        "productprice= '"+Double.parseDouble(addProduct_price.getText())+"',"+
                        "productquantity='"+Integer.parseInt(addProduct_quantity.getText())+"', productimage = '"+uri+"' where productmodel ='"+
                        addProduct_model.getText()+"'";
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(("Confirmation Message"));
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update the product?");
                Optional<ButtonType> option=alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    stmt=c.conn.createStatement();
                    stmt.executeUpdate(sql);
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();
                    if(addProduct_type.getText().equals("Laptop"))
                    {
                        addLaptopProductsShowData();
                        laptop_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
                        accesories_btn.setStyle("-fx-background-color: transparent");
                        monitor_btn.setStyle("-fx-background-color: transparent");
                        components_btn.setStyle("-fx-background-color: transparent");
                    }
                    else if(addProduct_type.getText().equals("Monitor"))
                    {
                        addMonitorProductsShowData();
                        monitor_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
                        accesories_btn.setStyle("-fx-background-color: transparent");
                        laptop_btn.setStyle("-fx-background-color: transparent");
                        components_btn.setStyle("-fx-background-color: transparent");
                    }
                    else if(addProduct_type.getText().equals("Accessories"))
                    {
                        addAccessoriesProductsShowData();
                        accesories_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
                        laptop_btn.setStyle("-fx-background-color: transparent");
                        monitor_btn.setStyle("-fx-background-color: transparent");
                        components_btn.setStyle("-fx-background-color: transparent");
                    }
                    else if(addProduct_type.getText().equals("Components"))
                    {
                        addComponentsProductsShowData();
                        components_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
                        accesories_btn.setStyle("-fx-background-color: transparent");
                        monitor_btn.setStyle("-fx-background-color: transparent");
                        laptop_btn.setStyle("-fx-background-color: transparent");
                    }
                    addProductReset();
                }
                else
                {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addProductDelete()
    {
        String sql="Delete from product where productmodel='"+
                addProduct_model.getText()+"'";

        database c=new database();
        c.connectDb();
        try
        {
            Alert alert;

            if(addProduct_model.getText().isEmpty()||
                    addProduct_brandName.getText().isEmpty()||
                    addProduct_type.getText().isEmpty()||
                    addProduct_price.getText().isEmpty()||addProduct_description.getText().isEmpty()
                    ||addProduct_quantity.getText().isEmpty()) {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else
            {
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(("Confirmation Message"));
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the product?");
                Optional<ButtonType> option=alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    stmt=c.conn.createStatement();
                    stmt.executeUpdate(sql);
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();


                    if(addProduct_type.getText().equals("Laptop"))
                    {
                        addLaptopProductsShowData();
                        addProductReset();
                    }
                    else if(addProduct_type.getText().equals("Monitor"))
                    {
                        addMonitorProductsShowData();
                        addProductReset();
                    }
                    else if(addProduct_type.getText().equals("Accessories"))
                    {
                        addAccessoriesProductsShowData();
                        addProductReset();
                    }
                    else if(addProduct_type.getText().equals("Components"))
                    {
                        addComponentsProductsShowData();
                        addProductReset();
                    }
                }
                else
                {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addLaptopProductSearch()
    {
        System.out.println("Laptop");
        FilteredList<productData>filter=new FilteredList<>(addLaptopProductList,b->true);
        addProduct_search.textProperty().addListener((observable,oldValue,newValue)->
        {
            filter.setPredicate(predicateProductData-> {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String searchkey=newValue.toLowerCase();
                if(predicateProductData.getProductModel().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("laptop"))
                {
                    return true;
                }
                else if(predicateProductData.getProductBrand().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("laptop"))
                {
                    return true;
                }


                else if(predicateProductData.getProductDescription().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("laptop"))
                {
                    return true;
                }
                else if(predicateProductData.getProductPrice().toString().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("laptop"))
                {
                    return true;
                }
                else if(predicateProductData.getProductQuantity().toString().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("laptop"))
                {
                    return true;
                }
                else
                {
                    System.out.println(predicateProductData.getProductModel());
                    return false;
                }
            });
            SortedList<productData>sortList=new SortedList<>(filter);

            sortList.comparatorProperty().bind(addProduct_tableView.comparatorProperty());

            addProduct_tableView.setItems(sortList);
            System.out.println("DoneLaptop");

        });



    }
    public void addDesktopProductSearch()
    {
        System.out.println("Desktop");
        FilteredList<productData>filter=new FilteredList<>(addMonitorProductList,b->true);
        addProduct_search.textProperty().addListener((observable,oldValue,newValue)->
        {
            filter.setPredicate(predicateProductData-> {
                if (newValue == null || newValue.isEmpty()||newValue.isBlank())
                {
                    return true;
                }
                String searchkey=newValue.toLowerCase();
                if(predicateProductData.getProductModel().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("desktop"))
                {
                    return true;
                }
                else if(predicateProductData.getProductBrand().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("desktop"))
                {

                    return true;
                }


                else if(predicateProductData.getProductDescription().toLowerCase().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("desktop"))
                {

                    return true;
                }
                else if(predicateProductData.getProductPrice().toString().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("desktop"))
                {

                    return true;
                }
                else if(predicateProductData.getProductQuantity().toString().indexOf(searchkey)>-1
                        && predicateProductData.getProductType().toLowerCase().equals("desktop"))
                {

                    return true;
                }
                else
                {

                    return false;
                }

            });
            SortedList<productData>sortList=new SortedList<>(filter);
            sortList.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
            addProduct_tableView.setItems(sortList);
            System.out.println("DoneDesktop");
        });
    }
    public void addAccesoriesProductSearch()
    {

        FilteredList<productData>filter=new FilteredList<>(addAccessoriesProductList,e->true);
        addProduct_search.textProperty().addListener((observable,oldValue,newValue)->
        {
            filter.setPredicate(predicateProductData-> {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String searchkey=newValue.toLowerCase();
                if(predicateProductData.getProductModel().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductBrand().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }


                else if(predicateProductData.getProductDescription().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductPrice().toString().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductQuantity().toString().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            });
            SortedList<productData>sortList=new SortedList<>(filter);
            sortList.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
            addProduct_tableView.setItems(sortList);
        });
    }
    public void addComponentsProductSearch()
    {
        FilteredList<productData>filter=new FilteredList<>(addComponentsProductList,e->true);
        addProduct_search.textProperty().addListener((observable,oldValue,newValue)->
        {
            filter.setPredicate(predicateProductData-> {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String searchkey=newValue.toLowerCase();
                if(predicateProductData.getProductModel().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductBrand().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }

                else if(predicateProductData.getProductDescription().toLowerCase().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductPrice().toString().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else if(predicateProductData.getProductQuantity().toString().indexOf(searchkey)!=-1)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            });
            SortedList<productData>sortList=new SortedList<>(filter);
            sortList.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
            addProduct_tableView.setItems(sortList);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        addLaptopProductsShowData();
        laptop_btn.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
        accesories_btn.setStyle("-fx-background-color: transparent");
        monitor_btn.setStyle("-fx-background-color: transparent");
        components_btn.setStyle("-fx-background-color: transparent");
        addLaptopProductSearch();
    }
}
