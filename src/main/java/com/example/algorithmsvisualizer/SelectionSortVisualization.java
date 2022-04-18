package com.example.algorithmsvisualizer;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SelectionSortVisualization extends AlgorithmVisualization{
    private int length;
    private double speed;
    private String bigONotation;
    private int[] values;
    private Line[] lines;
    private HBox hbox;
    private Label clockLbl;
    private Thread thread;
    private boolean isVisualizationOn;

    public SelectionSortVisualization(int values[], HBox hbox, Label clockLbl, double speed){
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
            // One by one move boundary of unsorted subarray
            for (int i = 0; i < this.length-1; i++){
                // Find the minimum element in unsorted array
                int min_idx = i;
                for (int j = i+1; j < this.length; j++){
                    int line1index = hbox.getChildren().indexOf(lines[j]);
                    //////founded new smallest and making it ///////////////
                    if (this.values[j] < this.values[min_idx]){
                        try{/*Changing old smallest to normal color*/
                            ((Line) hbox.getChildren().get(hbox.getChildren().indexOf(lines[min_idx]))).setStroke(Color.rgb(220, 156, 253));
                        }catch(IndexOutOfBoundsException e){
                            Thread.currentThread().stop();
                        }
                        ////making new smallest
                        min_idx = j;
                        /////giving new smallest color
                        try{
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.RED);
                        }catch(IndexOutOfBoundsException e){
                            Thread.currentThread().stop();
                        }
                        try{
                            Thread.sleep((long)(1000/speed));
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        ///////////White color to visualize the process of looking
                        try{
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.WHITE);
                        }catch(IndexOutOfBoundsException e){
                            Thread.currentThread().stop();
                        }
                        //////waiting in order for user to see///////////
                        try{
                            Thread.sleep((long)(1000/speed));
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        //////change color to default///////////
                        try{
                            ((Line) hbox.getChildren().get(line1index)).setStroke(Color.rgb(220, 156, 253));
                        }catch(IndexOutOfBoundsException e){
                            Thread.currentThread().stop();
                        }
                    }
                }

                // Swap the found minimum element with the first
                // element
                int temp = this.values[min_idx];
                this.values[min_idx] = this.values[i];
                this.values[i] = temp;

                int line1index = hbox.getChildren().indexOf(lines[min_idx]);
                int line2index = hbox.getChildren().indexOf(lines[i]);
                try{
                    int line1endY = (int)((Line)hbox.getChildren().get(line1index)).getEndY();
                    ((Line) hbox.getChildren().get(line1index)).setStroke(Color.GREEN);
                    ((Line) hbox.getChildren().get(line2index)).setStroke(Color.GREEN);
                    ((Line)hbox.getChildren().get(line1index)).setEndY(((Line)hbox.getChildren().get(line2index)).getEndY());
                    ((Line)hbox.getChildren().get(line2index)).setEndY(line1endY);
                }catch(IndexOutOfBoundsException e){
                    Thread.currentThread().stop();
                }
                //////waiting in order for user to see///////////
                try{
                    Thread.sleep((long)(1000/speed));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                try{
                    ((Line) hbox.getChildren().get(line1index)).setStroke(Color.rgb(220, 156, 253));
                    ((Line) hbox.getChildren().get(line2index)).setStroke(Color.rgb(220, 156, 253));
                }catch(IndexOutOfBoundsException e){
                    Thread.currentThread().stop();
                }
            }
        });
        this.thread = thread;
        this.thread.start();
        super.thread = this.thread;
        this.isVisualizationOn = true;
    }
}
