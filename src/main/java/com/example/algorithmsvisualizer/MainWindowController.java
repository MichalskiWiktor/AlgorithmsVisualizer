package com.example.algorithmsvisualizer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Random;

public class MainWindowController{
    @FXML ComboBox algorithmPicker;
    @FXML TextField noOfElementsField;
    @FXML Slider speedSlider;
    @FXML HBox hbox = new HBox();
    @FXML public void initialize(){
        this.algorithmPicker.getItems().addAll("Bumble Sort", "Selection Sort", "Insertion Sort", "Quick Sort");
        this.algorithmPicker.getSelectionModel().selectFirst();
    }

    public void createAlgorithm(){
        hbox.getChildren().clear();
        int numElements = 0;
        /*Checking if user inputted correct numbers*/
        try{
            numElements = Integer.parseInt(this.noOfElementsField.getText());
        }catch(NumberFormatException e){
            System.out.println("It needs to be a number");
        }
        if(numElements>30){
            numElements = 30;
            this.noOfElementsField.setText("30");
        }
        if(numElements>=2){
            Algorithm algorithm;
            switch(this.algorithmPicker.getSelectionModel().getSelectedItem().toString()){
                case "Bumble Sort" -> algorithm = new BubbleSort(getRandomNumbers(numElements), this.hbox, this.speedSlider.getValue());
                default -> {
                    System.out.println("error");
                    algorithm = new Algorithm();
                }
            }
            algorithm.drawElements();
            algorithm.drawSortingVisualization();
        }else{
            System.out.println("The number must be bigger than 1");
        }
    }
    public int[] getRandomNumbers(int len){
        Random rand = new Random();
        int upperbound = 42;
        int[] nums = new int[len];
        for(int i=0;i<len;i++){
            nums[i] = rand.nextInt(upperbound);
        }
        return nums;
    }
    public void generateRandom(){
    }
}