package model;

import controller.Sketch;
import javafx.scene.paint.Color;

public class Ellipse extends UniverseShape {
    private double[] endPoint;

    public Ellipse(double[] startPoint, double[] endPoint, Color strokeColor, Color fillColor, double strokeWidth) {
        super();
        this.setPosition(startPoint);
        this.setEndPoint(endPoint);
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
        if (Circle.class.isInstance(this)) {
            this.getProperties().put("type", Sketch.ShapeType.CIRCLE);
        } else
            this.getProperties().put("type", Sketch.ShapeType.ELLIPSE);
    }

    protected void fixDimensions() {
        if (getEndPoint()[0] - getPosition()[0] < 0) {
            double temp = getEndPoint()[0];
            getEndPoint()[0] = getPosition()[0];
            getPosition()[0] = temp;
        }
        if (getEndPoint()[1] - getPosition()[1] < 0) {
            double temp = getEndPoint()[1];
            getEndPoint()[1] = getPosition()[1];
            getPosition()[1] = temp;
        }
    }

    @Override
    public void draw() {
        getLayer().getGraphicsContext2D().setLineWidth(getStrokeWidth());
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().setFill(getFillColor());
        fixDimensions();
        getLayer().getGraphicsContext2D().fillOval(getPosition()[0], getPosition()[1], Math.abs(getEndPoint()[0] - getPosition()[0]), Math.abs(getEndPoint()[1] - getPosition()[1]));
        getLayer().getGraphicsContext2D().strokeOval(getPosition()[0], getPosition()[1], Math.abs(getEndPoint()[0] - getPosition()[0]), Math.abs(getEndPoint()[1] - getPosition()[1]));
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }

    public double[] getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(double[] endPoint) {
        this.endPoint = endPoint;
    }
}
