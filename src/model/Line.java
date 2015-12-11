package model;

import controller.Sketch;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


public class Line extends UniverseShape{
    double[] endPoint = new double[2];
    Canvas line;
    public Line(double[]startPoint,double[]endPoint,Color color){
        this.setPosition(startPoint);
        this.endPoint = endPoint;
        this.setColor(color);
        this.setFillColor(Color.BLACK);
        this.getProperties().put("starting-x", getPosition()[0]);
        this.getProperties().put("starting-y",getPosition()[1]);
        this.getProperties().put("ending-x",endPoint[0]);
        this.getProperties().put("ending-y",endPoint[1]);
        this.getProperties().put("color",getColor());
    }
    @Override
    public void draw(){
        line = new Canvas(620,540);
        line.getGraphicsContext2D().setStroke(getColor());
        line.getGraphicsContext2D().strokeLine(getPosition()[0], getPosition()[1], endPoint[0], endPoint[1]);
        Sketch.getInstance().getCanvas().getChildren().add(line);
    }
    @Override
    public void remove(){
        Sketch.getInstance().getCanvas().getChildren().remove(line);
    }
}
