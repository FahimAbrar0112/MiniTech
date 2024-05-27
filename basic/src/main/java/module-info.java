module com.example.basic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires kernel;
    requires layout;
    requires itextpdf;


    opens com.example.basic to javafx.fxml;
    exports com.example.basic;
}