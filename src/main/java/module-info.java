module com.example.cosc2440assessment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.naming;


    opens com.example.cosc2440assessment2 to javafx.fxml;
    exports com.example.cosc2440assessment2;
    exports com.example.cosc2440assessment2.controller;
    opens com.example.cosc2440assessment2.controller to javafx.fxml;
}