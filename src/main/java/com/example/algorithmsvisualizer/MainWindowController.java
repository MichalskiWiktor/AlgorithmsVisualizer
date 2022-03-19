package com.example.algorithmsvisualizer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class MainWindowController{
    @FXML ComboBox algorithmPicker;
    @FXML public void initialize(){
        algorithmPicker.getItems().addAll("Bumble Sort", "Selection Sort", "Insertion Sort", "Quick Sort");
        Algorithm algorithm1 = new BumbleSort();
        algorithm1.sort();
    }
    public void sort(){

    }
    public void generateRandom(){

    }
}