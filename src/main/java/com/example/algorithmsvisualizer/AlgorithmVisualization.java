package com.example.algorithmsvisualizer;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class AlgorithmVisualization{
    private int length;
    private double speed;
    private String bigONotation;
    private int[] values;
    private Line[] lines;
    private HBox hbox;
    private Label clockLbl;
    private Thread thread;
    private boolean isVisualizationOn;

    public abstract void drawSortingVisualization();
    public void drawElements(){
        for(int k=0;k<this.length;k++){
            lines[k] = new Line(0, k, 0, values[k]*8);
            lines[k].setStroke(Color.rgb(220, 156, 253));
            lines[k].setStrokeWidth(20.5);
        }
        this.hbox.getChildren().addAll(lines);
    }
    public void pauseVisualization(){
        this.thread.suspend();
    }
    public void resumeVisualization(){
        this.thread.resume();
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
}
