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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminHome implements Initializable {

    @FXML
    private PieChart pieChart=new PieChart();

    @FXML
    Label usernameshow;
    @FXML
    Pane pane;

    @FXML
    private Label active_employees;
    @FXML
    private AreaChart<String,Double> chart;
    @FXML
    private Button clear;
    @FXML
    private Button income;
    @FXML
    private Button dashboard;
    @FXML
    private Button employee;
    @FXML
    private TableColumn<Employee, String> employee_id;
    @FXML
    private TextField employee_id_text;
    @FXML
    private TableColumn<Employee, String> first_name;
    @FXML
    private TextField first_name_text;
    @FXML
    private AnchorPane fourth_form;
    @FXML
    private TableColumn<Employee, String> gender;
    @FXML
    private TextField gender_text;
    @FXML
    private TableColumn<Employee, String> last_name;
    @FXML
    private TextField last_name_text;
    @FXML
    private AnchorPane mainform;
    @FXML
    private Button product;
    @FXML
    private Button sell;
    @FXML
    private Button remove;
    @FXML
    private TableColumn<Employee, Double> salary;
    @FXML
    private TextField salary_text;
    @FXML
    private Button save;
    @FXML
    private AnchorPane second_form;
    @FXML
    private Button sign_out;
    @FXML
    private AnchorPane third_form;
    @FXML
    private Label todaysincome;
    @FXML
    private Label total_income;
    @FXML
    private Button update;
    @FXML
    private Button close_scene;
    @FXML
    private Button minimize_scene;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    Alert aLert;
    @FXML
    TableView<Employee> employee_tableview;

    private double x=0;
    private double y=0;
    private double a=0;
    private double b=0;


    @FXML
    public void logout(ActionEvent e) throws IOException {


        aLert=new Alert((Alert.AlertType.CONFIRMATION));
        aLert.setTitle("Confirmation Message");
        aLert.setHeaderText(null);
        aLert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType>option=aLert.showAndWait();
        if(option.get().equals(ButtonType.OK))
        {
            Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            scene=new Scene(root);
            root.setOnMousePressed((MouseEvent event)->{
                a=event.getScreenX();
                b=event.getScreenY();
            });
            root.setOnMouseDragged((MouseEvent event)->{
                stage.setX(event.getScreenX()-a);
                stage.setY(event.getScreenY()-b);
                stage.setOpacity(.8);
            });
            root.setOnMouseReleased((MouseEvent event)->{
                stage.setOpacity(1);
            });
            stage.setScene((scene));
            stage.show();

        }

    }



    @FXML
    public void switchform(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==dashboard)
        {
            second_form.setVisible(true);
            third_form.setVisible(true);
            fourth_form.setVisible(false);
            dashboard.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            product.setStyle("-fx-background-color: transparent");
            employee.setStyle("-fx-background-color: transparent");
            //income.setStyle("-fx-background-color: transparent");

            showactiveemployee();
            displaytodaysincome();
            displaytoal();
            piechartshow();
        }
        else if(event.getSource()==product)
        {
            root= FXMLLoader.load(this.getClass().getResource("AdminProduct.fxml"));
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
        else if(event.getSource()==sell)
        {
            root= FXMLLoader.load(this.getClass().getResource("sell.fxml"));
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
        else if(event.getSource()==employee)
        {
            second_form.setVisible(false);
            third_form.setVisible(false);
            fourth_form.setVisible(true);
            employee.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
            product.setStyle("-fx-background-color: transparent");
            dashboard.setStyle("-fx-background-color: transparent");

            employeeshowdata();
            if(event.getSource()==save)
            {
                addEmployeeAdd();
            }
            else if(event.getSource()==clear)
            {
                addEmployeeclear();
            }
            else if(event.getSource()==remove)
            {
                addEmployeeRemove();

            }
            else if(event.getSource()==update)
            {
                addEmployeeUpdate();

            }
        }
    }

    database data=new database();
    private PreparedStatement pst;
    private Statement stmt;
    private ResultSet result;

    public ObservableList<Employee> addEmployeelist()
    {
        ObservableList<Employee>emplist= FXCollections.observableArrayList();
        String sql="Select * from employee";
        data.connectDb();
        try {
            Employee emp;
            pst=data.conn.prepareStatement(sql);
            result=pst.executeQuery();
            while(result.next())
            {
                emp=new Employee(result.getString("employee_id")
                        ,result.getString("first_name")
                        ,result.getString("last_name")
                        ,result.getDouble("salary")
                        ,result.getString("gender"));
                emplist.add(emp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return emplist;

    }
    @FXML
    private ObservableList<Employee>employeslist;
    @FXML
    public void employeeshowdata()
    {
        employeslist=addEmployeelist();
        employee_id.setCellValueFactory(new PropertyValueFactory<>("Employee_Id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("First_Name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
        salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        employee_tableview.setItems(employeslist);
    }
    @FXML
    public void employeeselect()
    {
        Employee emp=employee_tableview.getSelectionModel().getSelectedItem();
        int num=employee_tableview.getSelectionModel().getSelectedIndex();
        if(num<0)
        {
            return;
        }
        employee_id_text.setText(emp.getEmployee_Id());
        first_name_text.setText(emp.getFirst_Name());
        last_name_text.setText(emp.getLast_Name());
        salary_text.setText(Double.toString(emp.getSalary()));
        gender_text.setText(emp.getGender());
    }
    public void addEmployeeAdd()
    {
        String insertProduct="Insert into employee "
                +"values(?,?,?,?,?)";
        database c=new database();
        c.connectDb();
        try
        {
            Alert alert;
            if(employee_id_text.getText().isEmpty()||
                    first_name_text.getText().isEmpty()||
                    last_name_text.getText().isEmpty()||
                    salary_text.getText().isEmpty()
                    ||gender_text.getText().isEmpty()) {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else
            {
                String check="Select * from employee where employee_id='"+employee_id_text.getText()+"' ";
                stmt=c.conn.createStatement();
                result=stmt.executeQuery(check);
                Boolean answer=false;
                if(result.next())
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Same Employee Id is already exist");
                    alert.showAndWait();
                }
                else
                {
                    pst=c.conn.prepareStatement(insertProduct);
                    pst.setString(1,employee_id_text.getText());
                    pst.setString(2,first_name_text.getText());
                    pst.setString(3,last_name_text.getText());
                    pst.setDouble(4,Double.parseDouble(salary_text.getText()));
                    pst.setString(5,gender_text.getText());

                    pst.executeUpdate();
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();
                    employeeshowdata();
                    addEmployeeclear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addEmployeeclear()
    {
        employee_id_text.setText("");
        first_name_text.setText("");
        last_name_text.setText("");
        salary_text.setText("");
        gender_text.setText("");

    }
    public void addEmployeeUpdate()
    {
        String sql="Update employee set " +
                "first_name='"+first_name_text.getText()+"',"+
                "last_name='"+last_name_text.getText()+"',"+
                "salary= '"+Double.parseDouble(salary_text.getText())+"', "+
                "gender= '"+gender_text.getText()+"'"+" where employee_id='"+
                employee_id_text.getText()+"'";

        database c=new database();
        c.connectDb();
        try
        {
            Alert alert;
            if(employee_id_text.getText().isEmpty()||
                    first_name_text.getText().isEmpty()||
                    last_name_text.getText().isEmpty()||
                    salary_text.getText().isEmpty()
                    ||gender_text.getText().isEmpty()) {
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
                alert.setContentText("Are you sure you want to update the employee?");
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
                    employeeshowdata();
                    addEmployeeclear();
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
    public void addEmployeeRemove()
    {
        String sql="Delete from employee where employee_id='"+
                employee_id_text.getText()+"'";

        database data=new database();
        data.connectDb();
        try
        {
            Alert alert;
            if(employee_id_text.getText().isEmpty()||
                    first_name_text.getText().isEmpty()||
                    last_name_text.getText().isEmpty()||
                    salary_text.getText().isEmpty()
                    ||gender_text.getText().isEmpty()) {
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
                alert.setContentText("Are you sure you want to delete the employee?");
                Optional<ButtonType> option=alert.showAndWait();
                if(option.get().equals(ButtonType.OK))
                {
                    stmt=data.conn.createStatement();
                    stmt.executeUpdate(sql);
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();
                    employeeshowdata();
                    addEmployeeclear();
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
    @FXML
    public void displaylabel()
    {
        usernameshow.setText(getData.username);

    }
    public void close()
    {
        System.exit(0);
    }
    public void minimize()
    {
        Stage stage=(Stage)mainform.getScene().getWindow();
        stage.setIconified(true);
    }

    public void showactiveemployee() throws SQLException {
        String sql="select * from employee";
        database c=new database();
        c.connectDb();
        stmt=c.conn.createStatement();
        ResultSet rs3=stmt.executeQuery(sql);
        Integer CountE=0;
        while(rs3.next())
        {
            CountE++;
        }
        active_employees.setText(String.valueOf(CountE));
    }
    public void displaytodaysincome() throws SQLException {
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql="Select total from customer_receipt where date= '"+
                java.time.LocalDate.now()+"'";
        System.out.println("aass "+java.time.LocalDate.now());
        database c=new database();
        c.connectDb();
        stmt=c.conn.createStatement();
        ResultSet rs3=stmt.executeQuery(sql);
        Double sum=0.0;
        while(rs3.next())
        {
            System.out.println("aaaa "+sum);
            sum+=rs3.getDouble("total");
        }
        todaysincome.setText("$ "+sum);

    }
    public void displaytoal() throws SQLException {
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql="Select * from customer_receipt";
        database c=new database();
        c.connectDb();
        stmt=c.conn.createStatement();
        ResultSet rs3=stmt.executeQuery(sql);
        Double sum=0.0;
        while(rs3.next())
        {
            sum+=rs3.getDouble("total");
        }
        total_income.setText("$ "+sum);

    }
    public void dashboardChart()
    {
        pane.setVisible(false);
        chart.setVisible(true);
        pieChart.setVisible(false);
        chart.getData().clear();
        String sql="Select date,SUM(total) from customer_receipt GROUP BY date Order by date ASC limit 9";
        database c=new database();
        c.connectDb();
        try
        {
            XYChart.Series chartt=new XYChart.Series();
            pst=c.conn.prepareStatement(sql);
            ResultSet rs4=pst.executeQuery();
            while(rs4.next())
            {

                String date=rs4.getDate("date").toString();
                Double sum=rs4.getDouble("SUM");
                System.out.println(date+" "+sum);
                chartt.getData().add(new XYChart.Data(date,sum));
            }
            chartt.setName("Areachart");
            chart.getData().add(chartt);
            chart.setTitle("Income Per Day");





        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void piechartshow() throws SQLException {
        String sql1="Select SUM(productquantity) as SUMM from product where producttype='Laptop'";
        String sql2="Select SUM(productquantity) from product where producttype='Monitor'";
        String sql3="Select SUM(productquantity) from product where producttype='Components'";
        String sql4="Select SUM(productquantity) from product where producttype='Accessories'";
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
            count1=rs1.getInt(1);
        }
        ResultSet rs2=stmt.executeQuery(sql2);
        while(rs2.next())
        {
            count2=rs2.getInt(1);
        }
        ResultSet rs3=stmt.executeQuery(sql3);
        while(rs3.next())
        {
            count3=rs3.getInt(1);
        }
        ResultSet rs4=stmt.executeQuery(sql4);
        while(rs4.next())
        {
            count4=rs4.getInt(1);
        }
        System.out.println(count1+" "+count2+" "+count3+" "+count4);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Laptop", count1),
                new PieChart.Data("Monitor", count2),
                new PieChart.Data("Accessories", count4),
                new PieChart.Data("Components", count3));
        pane.setVisible(true);
        chart.setVisible(false);
        pieChart.setVisible(true);
        pieChart.setData(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);
        pieChart.setTitle("Products Counts");
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

    }
    public void incomepress() throws SQLException {
        second_form.setVisible(true);
        third_form.setVisible(true);
        fourth_form.setVisible(false);
        //income.setStyle(  "-fx-background-color:linear-gradient(to right, #065ba2, #042136)");
        product.setStyle("-fx-background-color: transparent");
        employee.setStyle("-fx-background-color: transparent");
        dashboard.setStyle("-fx-background-color: transparent");

        showactiveemployee();
        displaytodaysincome();
        displaytoal();
        dashboardChart();
    }










    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaylabel();
        second_form.setVisible(true);
        third_form.setVisible(true);
        fourth_form.setVisible(false);
        try {
            showactiveemployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            displaytodaysincome();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            displaytoal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dashboardChart();
        try {
            piechartshow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
