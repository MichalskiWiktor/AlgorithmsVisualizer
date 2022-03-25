package com.example.algorithmsvisualizer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.util.Random;

public class MainWindowController{
    @FXML ComboBox algorithmPicker;
    @FXML TextField noOfElementsField;
    @FXML HBox hbox = new HBox();
    @FXML public void initialize(){
        algorithmPicker.getItems().addAll("Bumble Sort", "Selection Sort", "Insertion Sort", "Quick Sort");
    }

    public void sort(){
        int numElements = Integer.parseInt(noOfElementsField.getText().toString());
        if(numElements>0){
            Algorithm algorithm;
            switch(algorithmPicker.getSelectionModel().getSelectedItem().toString()){
                case "Bumble Sort":
                    algorithm = new BubbleSort(getArrayWithRandomNumbers(numElements));
                    break;
                default:{
                    System.out.println("error");
                    algorithm = new Algorithm();
                }

            }
            Line[] lines = algorithm.draw();
            System.out.println(lines.length);
            this.hbox.getChildren().addAll(lines);
        }
        else{
            System.out.println("error");
        }
    }
    public float[] getArrayWithRandomNumbers(int len){
        Random rand = new Random();
        int upperbound = 100;
        float[] nums = new float[len];
        for(int i=0;i<len;i++){
            nums[i] = rand.nextFloat(upperbound);
        }
        return nums;
    }
    public void generateRandom(){
    }
}