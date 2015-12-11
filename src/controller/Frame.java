package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

import static controller.Sketch.ShapeType;

public class Frame {
    private static Frame instance;
    @FXML
    private ColorPicker strokeColor;
    @FXML
    private ColorPicker fillColor;
    @FXML
    private void initialize(){
        instance = this;
        strokeColor.valueProperty().addListener(l->Sketch.getInstance().setStrokeColor(strokeColor.getValue()));
        fillColor.valueProperty().addListener(l->Sketch.getInstance().setFillColor(fillColor.getValue()));
    }
    @FXML
    private void colorSelectPressed(){

    }
    @FXML
    private void rectanglePressed(){
        Sketch.getInstance().setShapeType(ShapeType.RECTANGLE);
    }
    @FXML
    private void trianglePressed(){
        Sketch.getInstance().setShapeType(ShapeType.TRIANGLE);
    }
    @FXML
    private void circlePressed(){
        Sketch.getInstance().setShapeType(ShapeType.CIRCLE);
    }
    @FXML
    private void linePressed(){
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
