package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Map;

public interface Shape {
    double[] getPosition();

    void setPosition(double[] point);

    Map getProperties();

    Color getColor();

    void setColor(Color color);

    Canvas getLayer();

    Color getFillColor();

    void setFillColor(Color color);

    void draw();
    void remove();
}
