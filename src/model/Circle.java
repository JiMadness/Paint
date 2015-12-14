package model;

import controller.Sketch;
import javafx.scene.paint.Color;

public class Circle extends Ellipse {
    public Circle(double[] startPoint, double[] endPoint, Color strokeColor, Color fillColor, double strokeWidth) {
        super(startPoint, endPoint, strokeColor, fillColor, strokeWidth);
    }

    @Override
    public void draw() {
        getLayer().getGraphicsContext2D().setLineWidth(getStrokeWidth());
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().setFill(getFillColor());
        fixDimensions();
        getLayer().getGraphicsContext2D().fillOval(getPosition()[0], getPosition()[1], Math.abs(getEndPoint()[0] - getPosition()[0]), Math.abs(getEndPoint()[0] - getPosition()[0]));
        getLayer().getGraphicsContext2D().strokeOval(getPosition()[0], getPosition()[1], Math.abs(getEndPoint()[0] - getPosition()[0]), Math.abs(getEndPoint()[0] - getPosition()[0]));
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }
}
