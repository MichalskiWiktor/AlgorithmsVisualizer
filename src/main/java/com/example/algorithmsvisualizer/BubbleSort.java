package com.example.algorithmsvisualizer;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BubbleSort extends Algorithm{
    private int length;
    private double speed;
    private String bigONotation;
    private int[] values;
    private Line[] lines;
    private HBox hbox;
    private Label clockLbl;

    public BubbleSort(int values[], HBox hbox, Label clockLbl, double speed){
        this.values = values;
        this.hbox = hbox;
        this.clockLbl = clockLbl;
        this.speed = speed;
        this.length = this.values.length;
        this.lines = new Line[this.length];
        this.bigONotation = "O(n^2)";
    }
    public void drawSortingVisualization() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < length-1; i++){
                for (int j = 0; j < length-i-1; j++){
                    if (values[j] > values[j+1]) {
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
                        int temp = values[j];
                        values[j] = values[j+1];
                        values[j+1] = temp;
                        try{
                            Thread.sleep((long)(1000/speed));
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        try{
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.rgb(220, 156, 253));
                        }catch(IndexOutOfBoundsException e){
                            Thread.currentThread().stop();
                        }
                    }
                }
            }
            for(int i=0;i<length;i++){
                System.out.println(values[i]);
            }
        });
        thread.start();
    }
    public void drawElements(){
        for(int k=0;k<this.length;k++){
            lines[k] = new Line(0, k, 0, values[k]*8);
            lines[k].setStroke(Color.rgb(220, 156, 253));
            lines[k].setStrokeWidth(20.5);
        }
        this.hbox.getChildren().addAll(lines);
    }
    public String getBigONotation(){
        return this.bigONotation;
    }
}
