package model;

import controller.Sketch;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import main.Main;

import java.util.HashMap;
import java.util.Map;

public class UniverseShape implements Shape{
    Map<String, Double> properties = new HashMap<>();
    private double[] position = new double[2];
    private Color color;
    private Color fillColor;
    private double strokeWidth;
    private Canvas layer = new Canvas(Sketch.getInstance().getMainWidth(), Sketch.getInstance().getMainHeight());

    public UniverseShape() {
        Main.getInstance().getShapes().push(this);
    }
    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] point) {
        position = point;
    }

    public Map getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Double> properties) {
        this.properties=properties;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color=color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public Canvas getLayer(){
        return layer;
    }

    public void setLayer(Canvas layer) {
        this.layer = layer;
    }

    public void draw() {
    }

    public void remove(){
        Sketch.getInstance().getCanvas().getChildren().remove(getLayer());
        setLayer(new Canvas(Sketch.getInstance().getMainWidth(), Sketch.getInstance().getMainHeight()));
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
