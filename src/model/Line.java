package model;

import controller.Sketch;
import javafx.scene.paint.Color;


public class Line extends UniverseShape{
    private double[] endPoint = new double[2];
    private double lineWidth;

    public Line(double[]startPoint,double[]endPoint,Color color,double lineWidth){
        this.setPosition(startPoint);
        this.endPoint = endPoint;
        this.lineWidth= lineWidth;
        this.setColor(color);
        this.setFillColor(Color.BLACK);
        this.getProperties().put("starting-x", getPosition()[0]);
        this.getProperties().put("starting-y",getPosition()[1]);
        this.getProperties().put("ending-x",endPoint[0]);
        this.getProperties().put("ending-y",endPoint[1]);
        this.getProperties().put("line-width",lineWidth);
        this.getProperties().put("color",getColor());
    }
    @Override
    public void draw(){
        getLayer().getGraphicsContext2D().setLineWidth(lineWidth);
        getLayer().getGraphicsContext2D().setStroke(getColor());
        getLayer().getGraphicsContext2D().strokeLine(getPosition()[0], getPosition()[1], endPoint[0], endPoint[1]);
        Sketch.getInstance().getCanvas().getChildren().add(getLayer());
    }
}
