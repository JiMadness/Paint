package controller;

import javafx.fxml.FXML;

import static controller.Sketch.ShapeType;

public class Frame {
    private static Frame instance;
    @FXML
    private void initialize(){
        instance = this;
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
