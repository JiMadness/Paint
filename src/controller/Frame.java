package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import main.DataDealer;
import main.Main;
import model.Shape;

import java.util.Stack;

import static controller.Sketch.ShapeType;

public class Frame {
    private static Frame instance;
    @FXML
    private ColorPicker strokeColor;
    @FXML
    private ColorPicker fillColor;
    @FXML
    private Spinner<Integer> lineWidth;
    @FXML
    private Button rectangle;
    @FXML
    private Button triangle;
    @FXML
    private Button circle;
    @FXML
    private Button ellipse;
    @FXML
    private Button line;
    @FXML
    private Label coordinates;
    private Stack<Shape> undone = new Stack<>();

    public static Frame getInstance() {
        return instance;
    }

    @FXML
    private void initialize(){
        instance = this;
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(false);
        ellipse.setDisable(false);
        line.setDisable(true);
        strokeColor.setValue(Color.BLACK);
        strokeColor.valueProperty().addListener(l -> Sketch.getInstance().setStrokeColor(strokeColor.getValue()));
        fillColor.valueProperty().addListener(l -> Sketch.getInstance().setFillColor(fillColor.getValue()));
        lineWidth.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        lineWidth.valueProperty().addListener(l -> Sketch.getInstance().setLineWidth(lineWidth.getValue()));
    }

    @FXML
    private void rectanglePressed(){
        rectangle.setDisable(true);
        triangle.setDisable(false);
        ellipse.setDisable(false);
        circle.setDisable(false);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.RECTANGLE);
    }

    @FXML
    private void trianglePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(true);
        circle.setDisable(false);
        ellipse.setDisable(false);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.TRIANGLE);
    }

    @FXML
    private void circlePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(true);
        ellipse.setDisable(false);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.CIRCLE);
    }

    @FXML
    private void linePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(false);
        ellipse.setDisable(false);
        line.setDisable(true);
        Sketch.getInstance().setShapeType(ShapeType.LINE);
    }

    @FXML
    private void ellipsePressed() {
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(false);
        ellipse.setDisable(true);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.ELLIPSE);
    }

    @FXML
    private void movePressed() {
        Sketch.getInstance().setMoveFlag(true);
        Sketch.getInstance().setResizeFlag(false);
        Sketch.getInstance().setDeleteFlag(false);
    }

    @FXML
    private void resizePressed(){
        Sketch.getInstance().setMoveFlag(false);
        Sketch.getInstance().setResizeFlag(true);
        Sketch.getInstance().setDeleteFlag(false);
    }

    @FXML
    private void deletePressed() {
        Sketch.getInstance().setMoveFlag(false);
        Sketch.getInstance().setResizeFlag(false);
        Sketch.getInstance().setDeleteFlag(true);
    }

    @FXML
    private void undoPressed() {
        if (!Main.getInstance().getShapes().isEmpty()) {
            undone.push(Main.getInstance().getShapes().pop());
            undone.peek().remove();
        }
    }

    @FXML
    private void redoPressed(){
        if (!undone.isEmpty()) {
            Main.getInstance().getShapes().push(undone.pop());
            Main.getInstance().getShapes().peek().draw();
        }
    }

    @FXML
    private void newPressed() {
    }

    @FXML
    private void openPressed() {
        DataDealer.load();
    }

    @FXML
    private void savePressed() {
        DataDealer.save();
    }
    public Label getCoordinates() {
        return coordinates;
    }
}
