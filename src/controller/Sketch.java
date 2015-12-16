package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Main;
import model.*;

import java.util.ArrayList;

public class Sketch {
    private static Sketch instance;
    private Color fillColor=Color.BLACK;
    private Color strokeColor=Color.BLACK;
    private boolean moveFlag;
    private boolean resizeFlag;
    private boolean deleteFlag;
    private double mainWidth = 1195;
    private double mainHeight = 629;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double lineWidth;
    private byte pointsIterator = 0;
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

    public double getMainWidth() {
        return mainWidth;
    }

    public void setMainWidth(double mainWidth) {
        this.mainWidth = mainWidth;
    }

    public double getMainHeight() {
        return mainHeight;
    }

    public void setMainHeight(double mainHeight) {
        this.mainHeight = mainHeight;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @FXML
    private void initialize(){
        double[] XPoints = new double[3];
        double[] YPoints = new double[3];

        setInstance(this);

        Main.getInstance().getRoot().setCenter(canvas);

        canvas.setMaxWidth(mainWidth);
        canvas.setMaxHeight(mainHeight);
        baseCanvas.setWidth(mainWidth);
        baseCanvas.setHeight(mainHeight);

        GraphicsContext gc= baseCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, baseCanvas.getWidth(), baseCanvas.getHeight());
        setFillColor(Color.WHITE);

        canvas.setOnMouseMoved(e ->
                        Frame.getInstance().getCoordinates().setText('\t' + "X: " + e.getX() + '\t' + "Y: " + e.getY())
        );
        canvas.setOnMousePressed(e -> {
            if (moveFlag) {
                return;
            }
            if (resizeFlag) {
                return;
            }
            if (deleteFlag) {
                return;
            }
            startX = e.getX();
            startY = e.getY();
            SmallRectangles.create(e.getX(), e.getY());
            if (shapeType == ShapeType.TRIANGLE) {
                XPoints[pointsIterator] = e.getX();
                YPoints[pointsIterator] = e.getY();
                pointsIterator++;
            }
        });

        canvas.setOnMouseReleased(e->{
            if (moveFlag) {
                moveFlag = false;
                return;
            }
            if (resizeFlag) {
                resizeFlag = false;
                return;
            }
            if (deleteFlag) {
                deleteFlag = false;
                return;
            }
            endX=e.getX();
            endY=e.getY();
            switch (shapeType){
                case LINE:
                    SmallRectangles.remove();
                    new Line(new double[]{startX, startY}, new double[]{endX, endY}, getStrokeColor(), getLineWidth() == 0 ? 1 : getLineWidth()).draw();
                    break;
                case CIRCLE:
                    SmallRectangles.remove();
                    new Circle(new double[]{startX, startY}, new double[]{endX, endY}, getStrokeColor(), getFillColor(), getLineWidth() == 0 ? 1 : getLineWidth()).draw();
                    break;
                case ELLIPSE:
                    SmallRectangles.remove();
                    new Ellipse(new double[]{startX, startY}, new double[]{endX, endY}, getStrokeColor(), getFillColor(), getLineWidth() == 0 ? 1 : getLineWidth()).draw();
                    break;
                case TRIANGLE:
                    if (pointsIterator == 3) {
                        SmallRectangles.remove();
                        new Triangle(XPoints, YPoints, getStrokeColor(), getFillColor(), getLineWidth()).draw();
                        pointsIterator = 0;
                    }
                    break;
                case RECTANGLE:
                    SmallRectangles.remove();
                    new Rectangle(new double[]{startX, startY}, new double[]{endX, endY}, getStrokeColor(), getFillColor(), getLineWidth() == 0 ? 1 : getLineWidth()).draw();
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

    public void setMoveFlag(boolean moveFlag) {
        this.moveFlag = moveFlag;
    }

    public void setResizeFlag(boolean resizeFlag) {
        this.resizeFlag = resizeFlag;
    }

    public enum ShapeType {LINE, CIRCLE, TRIANGLE, RECTANGLE, ELLIPSE}
}

class SmallRectangles {
    private static ArrayList<Canvas> smallRectangles = new ArrayList<>();

    public static void create(double x, double y) {
        Canvas c = new Canvas(Sketch.getInstance().getMainWidth(), Sketch.getInstance().getMainHeight());
        c.getGraphicsContext2D().strokeRect(x, y, 2, 2);
        smallRectangles.add(c);
        Sketch.getInstance().getCanvas().getChildren().add(c);
    }

    public static void remove() {
        Sketch.getInstance().getCanvas().getChildren().removeAll(smallRectangles);
    }
}