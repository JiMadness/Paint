package model;

import controller.Sketch;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


import java.util.HashMap;
import java.util.Map;

public class UniverseShape implements Shape{
    private double[] position = new double[2];
    private Color color;
    private Color fillColor;
    private Canvas layer=new Canvas(620,540);
    Map<String,Double> properties = new HashMap<>();

    public void setPosition(double[] point) {
        position = point;
    }

    public double[] getPosition() {
        return position;
    }

    public void setProperties(Map<String, Double> properties) {
        this.properties=properties;
    }

    public Map getProperties() {
        return properties;
    }

    public void setColor(Color color) {
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Canvas getLayer(){
        return layer;
    }

    public void draw() {
    }
    public void remove(){
        Sketch.getInstance().getCanvas().getChildren().remove(getLayer());
    }
}
