module com.example.colloseumgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colloseumgame to javafx.fxml;
    exports com.example.colloseumgame;
}