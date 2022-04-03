package com.example.algorithmsvisualizer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Random;

public class MainWindowController{
    @FXML ComboBox algorithmPicker;
    @FXML TextField noOfElementsField;
    @FXML Label bigONotationLbl;
    @FXML Label clockLbl;
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
                case "Bumble Sort" -> algorithm = new BubbleSort(getRandomNumbers(numElements), this.hbox, this.clockLbl, this.speedSlider.getValue());
                default -> {
                    System.out.println("error");
                    algorithm = new Algorithm();
                }
            }
            System.out.println(algorithm.getBigONotation());
            this.bigONotationLbl.setText(algorithm.getBigONotation());
            algorithm.drawElements();
            algorithm.drawSortingVisualization();
        }else{
            System.out.println("The number must be bigger than 1");
        }
    }
    public int[] getRandomNumbers(int len){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < len) {
            int random = randomGenerator.nextInt(42);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }
    public void generateRandom(){
    }
}