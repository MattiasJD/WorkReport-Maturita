module com.example.maturitazadani {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.maturitazadani to javafx.fxml;
    exports com.example.maturitazadani;
}