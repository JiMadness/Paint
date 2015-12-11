package model;

import javafx.scene.paint.Color;

import java.util.Map;

public interface Shape {
    void setPosition(double[] point);
    double[] getPosition();
    void setProperties(Map<String,Double> properties);
    Map getProperties();
    void setColor(Color color);
    Color getColor();
    void setFillColor(Color color);
    Color getFillColor();
    void draw();
    void remove();
}
