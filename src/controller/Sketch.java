package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Main;
import model.*;

public class Sketch {
    private static Sketch instance;
    public enum ShapeType{LINE,CIRCLE,TRIANGLE,RECTANGLE}
    private Color fillColor=Color.BLACK;
    private Color strokeColor=Color.BLACK;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double lineWidth;
    private ShapeType shapeType=ShapeType.LINE;
    @FXML
    private StackPane canvas;
    @FXML
    private Canvas baseCanvas;
    public static Sketch getInstance() {
        return instance;
    }

    public static void setInstance(Sketch instance) {
        Sketch.instance = instance;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @FXML
    private void initialize(){
        setInstance(this);

        Main.getInstance().getRoot().setCenter(canvas);

        GraphicsContext gc= baseCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, baseCanvas.getWidth(), baseCanvas.getHeight());

        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();
        });
        canvas.setOnMouseReleased(e->{
            endX=e.getX();
            endY=e.getY();
            switch (shapeType){
                case LINE:
                    new Line(new double[]{startX,startY},new double[]{endX,endY}, getStrokeColor(),getLineWidth()).draw();
                    break;
            }
        });
    }
    public StackPane getCanvas(){
        return canvas;
    }
    public void setShapeType(ShapeType shapeType){
        this.shapeType=shapeType;
    }
}
