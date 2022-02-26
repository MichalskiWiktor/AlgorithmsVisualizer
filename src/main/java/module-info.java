module com.example.algorithmsvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algorithmsvisualizer to javafx.fxml;
    exports com.example.algorithmsvisualizer;
}