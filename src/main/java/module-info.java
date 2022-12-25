module com.example.lr_7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.lr_7 to javafx.fxml;
    exports com.example.lr_7;
    exports com.example.lr_7.interfaces;
    opens com.example.lr_7.interfaces to javafx.fxml;
    exports com.example.lr_7.entities;
    opens com.example.lr_7.entities to javafx.fxml;
    exports com.example.lr_7.controllers;
    opens com.example.lr_7.controllers to javafx.fxml;
}