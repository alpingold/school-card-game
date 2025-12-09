module com.example.cardgamedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens com.example.cardgamedemo to javafx.fxml;
    exports com.example.cardgamedemo;
}