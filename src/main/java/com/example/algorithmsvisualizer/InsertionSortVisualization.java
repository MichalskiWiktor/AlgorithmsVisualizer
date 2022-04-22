package com.example.algorithmsvisualizer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class InsertionSortVisualization extends AlgorithmVisualization{
    private int length;
    private double speed;
    private String bigONotation;
    private int[] values;
    private Line[] lines;
    private HBox hbox;
    private Label clockLbl;
    private Thread thread;
    private boolean isVisualizationOn;

    public InsertionSortVisualization(int values[], HBox hbox, Label clockLbl, double speed){
        super(values, hbox, clockLbl, speed);
        this.values = values;
        this.hbox = hbox;
        this.clockLbl = clockLbl;
        this.speed = speed;
        this.length = this.values.length;
        this.lines = new Line[this.length];
        this.bigONotation = "O(n^2)";
        this.isVisualizationOn = false;
    }
    public void drawSortingVisualization() {
        this.lines = super.drawElements(); /////Drawing lines
        Thread thread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for (int i = 1; i < this.length; ++i) {
                int key = this.values[i];
                int j = i - 1;
                while (j >= 0 && this.values[j] > key) {
                    ////physical change
                    this.values[j + 1] = this.values[j];
                    ////visual change
                    int line1index = hbox.getChildren().indexOf(lines[j]);
                    int line2index = hbox.getChildren().indexOf(lines[j+1]);
                    try{
                        int line1endY = (int)((Line)hbox.getChildren().get(line1index)).getEndY();
                        ((Line) hbox.getChildren().get(line1index)).setStroke(Color.WHITE);
                        ((Line)hbox.getChildren().get(line1index)).setEndY(((Line)hbox.getChildren().get(line2index)).getEndY());
                        ((Line)hbox.getChildren().get(line2index)).setEndY(line1endY);
                    }catch(IndexOutOfBoundsException e){
                        Thread.currentThread().stop();
                    }
                    ////wait
                    try{
                        Thread.sleep((long)(1000/speed));
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    j--;
                    //////change color to default
                    try{
                        ((Line) hbox.getChildren().get(line1index)).setStroke(Color.rgb(220, 156, 253));
                    }catch(IndexOutOfBoundsException e){
                        Thread.currentThread().stop();
                    }
                }
                this.values[j + 1] = key;
            }
            long endTime = System.currentTimeMillis();
            long time = (endTime - startTime)/1000;
            if(time>60){
                Platform.runLater(() -> clockLbl.setText(time/60 + "m"));
            }
            else{
                Platform.runLater(() -> clockLbl.setText(time + "s"));
            }
        });
        this.thread = thread;
        this.thread.start();
        super.thread = this.thread;
        this.isVisualizationOn = true;
    }
}
