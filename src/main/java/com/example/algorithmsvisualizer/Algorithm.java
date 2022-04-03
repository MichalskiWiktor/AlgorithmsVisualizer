package com.example.algorithmsvisualizer;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class Algorithm{
    private int speed;
    private int noOfElements;
    private String bigONotation;
    public void drawSortingVisualization(){
        System.out.println("sortowanie");
    }
    public void drawElements(){
        System.out.println("wizualizowanie");
    }
    public String getBigONotation(){
        return this.bigONotation;
    }
}
