package model;

import controller.Sketch;
import javafx.scene.paint.Color;

public class Triangle extends UniverseShape {
    private double[] xPoints;
    private double[] yPoints;

    public Triangle(double[] xPoints, double[] yPoints, Color strokeColor, Color fillColor, double strokeWidth) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.setColor(strokeColor);
        this.setFillColor(fillColor);
        this.setStrokeWidth(strokeWidth);
        this.getProperties().put("point1-x", xPoints[0]);
        this.getProperties().put("point2-x", xPoints[1]);
        this.getProperties().put("point3-x", xPoints[2]);
        this.getProperties().put("point1-y", yPoints[0]);
        this.getProperties().put("point2-y", yPoints[1]);
        this.getProperties().put("point3-y", yPoints[2]);
        this.getProperties().put("stroke-width", strokeWidth);
        this.getProperties().put("stroke-color", getColor());
        this.getProperties().put("fill-color", getFillColor());
    }

    @Override
    public void draw() {
        getLayer().getGraphicsContext2D().setLineWidth(getStrokeWidth());
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().setFill(getFillColor());
        getLayer().getGraphicsContext2D().fillPolygon(xPoints, yPoints, 3);
        getLayer().getGraphicsContext2D().strokePolygon(xPoints, yPoints, 3);
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }

}
