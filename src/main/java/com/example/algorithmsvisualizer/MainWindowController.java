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
    AlgorithmVisualization algorithm;
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
        if(numElements>25){
            numElements = 25;
            this.noOfElementsField.setText("25");
        }else if(numElements<2){
            numElements = 2;
            this.noOfElementsField.setText("2");
        }
        /*Creating algorithm*/
        AlgorithmVisualization algorithm = null;
        switch(this.algorithmPicker.getSelectionModel().getSelectedItem().toString()){
            case "Bumble Sort" -> algorithm = new BubbleSortVisualization(getRandomNumbers(numElements, 42), this.hbox, this.clockLbl, this.speedSlider.getValue());
            default -> {
                System.out.println("error");
            }
        }
        /*Showing algorithm*/
        this.bigONotationLbl.setText(algorithm.getBigONotation());
        algorithm.drawElements();
        algorithm.drawSortingVisualization();
        this.algorithm = algorithm;
    }
    public int[] getRandomNumbers(int len, int max){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < len) {
            int random = randomGenerator.nextInt(max);
            if (!numbers.contains(random))
                numbers.add(random);
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }
    public void manageVisualization(){
        if(this.algorithm.getIsVisualizationOn()){
            this.algorithm.pauseVisualization();
            this.algorithm.setIsVisualizationOn(false);
        }
        else{
            this.algorithm.resumeVisualization();
            this.algorithm.setIsVisualizationOn(true);
        }
    }
    public void generateRandom(){
        Random randomGenerator = new Random();
        /*Type of algorithm*/
        this.algorithmPicker.setValue(this.algorithmPicker.getItems().get(randomGenerator.nextInt(3)));
        /*No of elements*/
        this.noOfElementsField.setText(String.valueOf(randomGenerator.nextInt(23) + 2));
        /*Speed*/
        this.speedSlider.setValue(randomGenerator.nextInt(10));
    }
}