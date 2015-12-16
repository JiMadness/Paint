package model;

import controller.Sketch;
import javafx.scene.paint.Color;

public class Rectangle extends UniverseShape {
    private double[] endPoint;

    public Rectangle(double[] startPoint, double[] endPoint, Color strokeColor, Color fillColor, double strokeWidth) {
        super();
        this.setPosition(startPoint);
        this.endPoint = endPoint;
        this.setColor(strokeColor);
        this.setFillColor(fillColor);
        this.setStrokeWidth(strokeWidth);
        this.getProperties().put("starting-x", getPosition()[0]);
        this.getProperties().put("starting-y", getPosition()[1]);
        this.getProperties().put("ending-x", endPoint[0]);
        this.getProperties().put("ending-y", endPoint[1]);
        this.getProperties().put("stroke-width", strokeWidth);
        this.getProperties().put("stroke-color", getColor());
        this.getProperties().put("fill-color", getFillColor());
        this.getProperties().put("type", Sketch.ShapeType.RECTANGLE);
    }

    private void fixDimensions() {
        if (endPoint[0] - getPosition()[0] < 0) {
            double temp = endPoint[0];
            endPoint[0] = getPosition()[0];
            getPosition()[0] = temp;
        }
        if (endPoint[1] - getPosition()[1] < 0) {
            double temp = endPoint[1];
            endPoint[1] = getPosition()[1];
            getPosition()[1] = temp;
        }
    }

    @Override
    public void draw() {
        getLayer().getGraphicsContext2D().setLineWidth(getStrokeWidth());
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().setFill(getFillColor());
        fixDimensions();
        getLayer().getGraphicsContext2D().fillRect(getPosition()[0], getPosition()[1], Math.abs(endPoint[0] - getPosition()[0]), Math.abs(endPoint[1] - getPosition()[1]));
        getLayer().getGraphicsContext2D().strokeRect(getPosition()[0], getPosition()[1], Math.abs(endPoint[0] - getPosition()[0]), Math.abs(endPoint[1] - getPosition()[1]));
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }
}
