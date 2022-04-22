package com.example.algorithmsvisualizer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class QuickSortVisualization extends AlgorithmVisualization{
    private int length;
    private double speed;
    private String bigONotation;
    private int[] values;
    private Line[] lines;
    private HBox hbox;
    private Label clockLbl;
    private Thread thread;
    private boolean isVisualizationOn;

    public QuickSortVisualization(int values[], HBox hbox, Label clockLbl, double speed){
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
    public void sort(int[] a, int left, int right, HBox newHbox){
        System.out.println(left);
        System.out.println(right);
        if (left < right){
            System.out.println("weszlo");
            int pivot = a[right];
            int pos = left - 1;
            /*Setting colors for pivot and pos*/
            try{
                ((Line) newHbox.getChildren().get(newHbox.getChildren().indexOf(pivot))).setStroke(Color.rgb(245, 106, 77));
                ((Line) newHbox.getChildren().get(newHbox.getChildren().indexOf(pos))).setStroke(Color.BLACK);
            }catch(IndexOutOfBoundsException e){
                Thread.currentThread().stop();
            }
            for (int i = left; i < right; i++){
                System.out.println("ok1");
                try{
                    ((Line) newHbox.getChildren().get(newHbox.getChildren().indexOf(i))).setStroke(Color.WHITE);
                }catch(IndexOutOfBoundsException e){
                    Thread.currentThread().stop();
                }
                if (a[i] <= pivot){
                    Swap(a, ++pos, i);
                    try{
                        ((Line) newHbox.getChildren().get(newHbox.getChildren().indexOf(++pos))).setStroke(Color.rgb(157, 228, 124));
                        ((Line) newHbox.getChildren().get(newHbox.getChildren().indexOf(i))).setStroke(Color.rgb(157, 228, 124));
                    }catch(IndexOutOfBoundsException e){
                        Thread.currentThread().stop();
                    }
                }
            }
            Swap(a, pos + 1, right);
            sort(a, left, pos, newHbox);
            sort(a, pos + 1, right, newHbox);
        }
    }
    public void Swap(int[] a, int i, int j){
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
    public void drawSortingVisualization() {
        this.lines = super.drawElements(); /////Drawing lines
        Thread thread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            sort(values, 0, length-1, hbox);
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
