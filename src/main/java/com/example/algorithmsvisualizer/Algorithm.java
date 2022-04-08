package com.example.algorithmsvisualizer;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class Algorithm{
    private int speed;
    private int noOfElements;
    private String bigONotation;
    private boolean isVisualizationOn;
    private Thread thread;
    public void drawSortingVisualization(){
        System.out.println("sortowanie");
    }
    public void drawElements(){
        System.out.println("wizualizowanie");
    }
    public String getBigONotation(){
        return this.bigONotation;
    }
    public Boolean getIsVisualizationOn(){
        return this.isVisualizationOn;
    }
    public void setIsVisualizationOn(boolean isVisualizationOn){
        this.isVisualizationOn = isVisualizationOn;
    }
    public void pauseThread(){
        this.thread.suspend();
    }
    public void resumeThread(){
        this.thread.resume();
    }
}
