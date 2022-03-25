package com.example.algorithmsvisualizer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BubbleSort extends Algorithm{
    private float[] values;
    private int length;
    private int j, i;
    /////z kontrollera wywoluje jakos funkcje i zwracam jakos obraz
    public BubbleSort(float arr[]){
        this.values = arr;
        this.length = values.length;
    }
    public void sort(){
        for (i = 0; i < this.length-1; i++)
            for (j = 0; j < length-i-1; j++)
                if (this.values[j] > this.values[j+1]) {
                    ///draw za kazdym razem jak jak jest swap
                    // swap arr[j+1] and arr[j]
                    float temp = this.values[j];
                    this.values[j] = this.values[j+1];
                    this.values[j+1] = temp;
                }
    }
    public Line[] draw(){
        ///change it to arraylist
        Line[] lines = new Line[length];
        for(int k=0;k<this.length;k++){
            lines[k] = new Line(0, k, 0, values[k]*30);
            lines[k].setStroke(Color.RED);
            lines[k].setStrokeWidth(5);

        }
        return lines;
    }
}
