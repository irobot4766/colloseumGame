module com.example.colloseumgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.colloseumgame to javafx.fxml;
    exports com.example.colloseumgame;
}