package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Main;
import java.awt.MouseInfo;

public class Sketch {
    private static Sketch instance;
    private GraphicsContext graphicsContext;
    private double startPointx;
    private double startPointy;
    private double endPointx;
    private double endPointy;
    @FXML
    private Canvas canvas;
    public static Sketch getInstance() {
        return instance;
    }
    public static void setInstance(Sketch instance) {
        Sketch.instance = instance;
    }

    @FXML
    private void initialize(){
        setInstance(this);
        setGraphicsContext(canvas.getGraphicsContext2D());
        graphicsContext.setFill(Color.WHITE);
        Main.getInstance().getRoot().setCenter(canvas);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    @FXML
    private void dragged(){
        System.out.println("Hi");
        startPointx = MouseInfo.getPointerInfo().getLocation().getX();
        startPointy = MouseInfo.getPointerInfo().getLocation().getY();
    }
    @FXML
    private void exited(){
        System.out.println("Bye");
        endPointx = MouseInfo.getPointerInfo().getLocation().getX();
        endPointy = MouseInfo.getPointerInfo().getLocation().getY();
        graphicsContext.strokeLine(startPointx+180,startPointy+60,endPointx-180,endPointy-60);
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
