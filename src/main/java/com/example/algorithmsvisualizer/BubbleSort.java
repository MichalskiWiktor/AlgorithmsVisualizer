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
        Thread thread = new Thread(){
            public void run(){
                for (int i = 0; i < length-1; i++){
                    for (int j = 0; j < length-i-1; j++){
                        if (values[j] > values[j+1]) {
                            int line1index = hbox.getChildren().indexOf(lines[j]);
                            int line2index = hbox.getChildren().indexOf(lines[j+1]);
                            double line1endY = ((Line)hbox.getChildren().get(line1index)).getEndY();
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.WHITE);
                            ((Line)hbox.getChildren().get(line1index)).setEndY(((Line)hbox.getChildren().get(line2index)).getEndY());
                            ((Line)hbox.getChildren().get(line2index)).setEndY(line1endY);
                            float temp = values[j];
                            values[j] = values[j+1];
                            values[j+1] = temp;
                            try{
                                Thread.sleep(100);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                                System.out.println("erro");
                            }
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.rgb(220, 156, 253));
                        }
                    }
                }
            }
        };
        thread.start();
        for(int i = 0; i < length-1; i++){
            System.out.println(values[i]);
        }
        ////after eending of visualizatyion add bigO notation and time it took to vinish algoritms vith picked speed
    }
    public void drawElements(){
        for(int k=0;k<this.length;k++){
            lines[k] = new Line(0, k, 0, values[k]*8);
            lines[k].setStroke(Color.rgb(220, 156, 253));
            lines[k].setStrokeWidth(20.5);
        }
        this.hbox.getChildren().addAll(lines);
    }
}
