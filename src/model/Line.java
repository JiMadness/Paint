package model;

import controller.Sketch;
import javafx.scene.paint.Color;


public class Line extends UniverseShape{
    private double[] endPoint = new double[2];

    public Line(double[] startPoint, double[] endPoint, Color color, double strokeWidth) {
        super();
        this.setPosition(startPoint);
        this.endPoint = endPoint;
        this.setStrokeWidth(strokeWidth);
        this.setColor(color);
        this.setFillColor(Color.BLACK);
        this.getProperties().put("starting-x", getPosition()[0]);
        this.getProperties().put("starting-y",getPosition()[1]);
        this.getProperties().put("ending-x",endPoint[0]);
        this.getProperties().put("ending-y", endPoint[1]);
        this.getProperties().put("stroke-width", strokeWidth);
        this.getProperties().put("stroke-color", getColor());
        this.getProperties().put("type", Sketch.ShapeType.LINE);
    }
    @Override
    public void draw(){
        getLayer().getGraphicsContext2D().setLineWidth(getStrokeWidth());
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().strokeLine(getPosition()[0], getPosition()[1], endPoint[0], endPoint[1]);
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }
}
