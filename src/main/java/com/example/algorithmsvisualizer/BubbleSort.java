package com.example.algorithmsvisualizer;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BubbleSort extends Algorithm{
    private float[] values;
    private int length;
    private HBox hbox;
    private Line[] lines;

    public BubbleSort(float arr[], HBox hbox){
        this.values = arr;
        this.length = this.values.length;
        this.hbox = hbox;
        this.lines = new Line[this.length];
    }
    public void drawSortingVisualization() {
        for (int i = 0; i < this.length-1; i++){
            for (int j = 0; j < this.length-i-1; j++){
                if (this.values[j] > this.values[j+1]) {
                    float temp = this.values[j];
                    this.values[j] = this.values[j+1];
                    this.values[j+1] = temp;
                    this.lines[j].setStroke(Color.WHITE);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    this.lines[j].setStroke(Color.RED);
                }
            }
        }
    }
    public void drawElements(){
        for(int k=0;k<this.length;k++){
            lines[k] = new Line(0, k, 0, values[k]*8);
            lines[k].setStroke(Color.RED);
            lines[k].setStrokeWidth(5);
        }
        this.hbox.getChildren().addAll(lines);
    }
}
