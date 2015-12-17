package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Main;
import model.*;

import java.util.ArrayList;
import java.util.Optional;

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
    private Shape tempShape;
    private double[] oldPoints = {-1, -1};
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
        canvas.setMaxWidth(mainWidth);
        GraphicsContext gc = baseCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, baseCanvas.getWidth(), baseCanvas.getHeight());
        setFillColor(Color.WHITE);
        baseCanvas.setWidth(mainWidth);
    }

    public double getMainHeight() {
        return mainHeight;
    }

    public void setMainHeight(double mainHeight) {
        this.mainHeight = mainHeight;
        canvas.setMaxHeight(mainHeight);
        GraphicsContext gc = baseCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, baseCanvas.getWidth(), baseCanvas.getHeight());
        setFillColor(Color.WHITE);
        baseCanvas.setHeight(mainHeight);
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @FXML
    private void initialize(){
        double[] XPoints = new double[3];
        double[] YPoints = new double[3];
        setInstance(this);

        setMainWidth(mainWidth);
        setMainHeight(mainHeight);

        canvas.setOnMouseMoved(e -> Frame.getInstance().getCoordinates().setText('\t' + "X: " + e.getX() + '\t' + "Y: " + e.getY())
        );
        canvas.setOnMousePressed(e -> {
            if (moveFlag || resizeFlag) {
                Shape s = ShapeSelector.getSelectedShape(e.getX(), e.getY());
                if (s != null) {
                    tempShape = s;
                    oldPoints[0] = e.getX();
                    oldPoints[1] = e.getY();
                }
                return;
            }
            if (deleteFlag) {
                Frame.getInstance().getDelete().setDisable(false);
                Shape s = ShapeSelector.getSelectedShape(e.getX(), e.getY());
                if (s != null) {
                    Main.getInstance().getShapes().remove(s);
                    s.remove();
                }
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
                Frame.getInstance().getMove().setDisable(false);
                moveFlag = false;
                if (tempShape != null) {
                    tempShape.getLayer().setTranslateX(e.getX() - oldPoints[0]);
                    tempShape.getLayer().setTranslateY(e.getY() - oldPoints[1]);
                    tempShape = null;
                }
                return;
            }
            if (resizeFlag) {
                Frame.getInstance().getResize().setDisable(false);
                resizeFlag = false;
                if (tempShape != null) {

                    TextInputDialog dialog = new TextInputDialog("2");
                    dialog.setTitle("Resize Object");
                    dialog.setHeaderText("How many times do you want to resize this object ?");
                    dialog.setContentText("Resize:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        tempShape.getLayer().setScaleX(Double.valueOf(result.get()));
                        tempShape.getLayer().setScaleY(Double.valueOf(result.get()));
                    }
                    tempShape = null;
                }
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

class ShapeSelector {
    public static Shape getSelectedShape(double x, double y) {
        for (Shape s : Main.getInstance().getShapes()) {
            if (s.getProperties().containsKey("point1-x")) {
                if (isInRange(x, (double) s.getProperties().get("point1-x"), y, (double) s.getProperties().get("point1-y")))
                    return s;
                if (isInRange(x, (double) s.getProperties().get("point2-x"), y, (double) s.getProperties().get("point2-y")))
                    return s;
                if (isInRange(x, (double) s.getProperties().get("point3-x"), y, (double) s.getProperties().get("point3-y")))
                    return s;
            } else {
                if (isInRange(x, (double) s.getProperties().get("starting-x"), y, (double) s.getProperties().get("starting-y")))
                    return s;
                if (isInRange(x, (double) s.getProperties().get("ending-x"), y, (double) s.getProperties().get("ending-y")))
                    return s;
            }
        }
        return null;
    }

    private static boolean isInRange(double x1, double x2, double y1, double y2) {
        return Math.abs(x1 - x2) < 5 && Math.abs(y1 - y2) < 5;
    }
}