package com.example.algorithmsvisualizer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class MainWindowController{
    @FXML ComboBox algorithmPicker;
    @FXML public void initialize(){
        algorithmPicker.getItems().addAll("Bumble Sort", "Selection Soft", "Insertion Sort", "Quick Sort");
    }
}