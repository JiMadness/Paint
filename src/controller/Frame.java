package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;

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
    private Button line;
    @FXML
    private void initialize(){
        instance = this;
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(false);
        line.setDisable(true);
        strokeColor.setValue(Color.BLACK);
        strokeColor.valueProperty().addListener(l -> Sketch.getInstance().setStrokeColor(strokeColor.getValue()));
        fillColor.valueProperty().addListener(l -> Sketch.getInstance().setFillColor(fillColor.getValue()));
        lineWidth.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        lineWidth.valueProperty().addListener(l->Sketch.getInstance().setLineWidth(lineWidth.getValue()));
    }

    @FXML
    private void rectanglePressed(){
        rectangle.setDisable(true);
        triangle.setDisable(false);
        circle.setDisable(false);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.RECTANGLE);
    }
    @FXML
    private void trianglePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(true);
        circle.setDisable(false);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.TRIANGLE);
    }
    @FXML
    private void circlePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(true);
        line.setDisable(false);
        Sketch.getInstance().setShapeType(ShapeType.CIRCLE);
    }
    @FXML
    private void linePressed(){
        rectangle.setDisable(false);
        triangle.setDisable(false);
        circle.setDisable(false);
        line.setDisable(true);
        Sketch.getInstance().setShapeType(ShapeType.LINE);
    }
    @FXML
    private void movePressed(){

    }
    @FXML
    private void resizePressed(){

    }
    @FXML
    private void undoPressed(){

    }
    @FXML
    private void redoPressed(){

    }
    public static Frame getInstance(){
        return instance;
    }
}
