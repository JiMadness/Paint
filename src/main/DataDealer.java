package main;

import controller.Sketch;
import controller.Sketch.ShapeType;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DataDealer {
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static File saveFile;

    public static void save() {
        try {
            if (saveFile == null)
                saveFile = getSaveFile();
            if (saveFile == null) return;
            FileWriter fileWriter = new FileWriter(saveFile);
            writer = new BufferedWriter(fileWriter);
            writer.write(Sketch.getInstance().getMainWidth() + ", " + Sketch.getInstance().getMainHeight() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Shape shape : Main.getInstance().getShapes()) {
            try {
                writer.write(shape.getProperties().toString() + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Alert(Alert.AlertType.INFORMATION, saveFile.getName() + " is saved successfully.").show();
    }

    public static void load() {
        File loadFile = getOpenFile();
        ArrayList<String> shapes = new ArrayList<>();
        Map<String, String> properties = new HashMap<>();
        if (loadFile == null) return;
        try {
            FileReader fileReader = new FileReader(loadFile);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                String line = reader.readLine();
                if (line != null)
                    shapes.add(line);
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.getInstance().getShapes().forEach(model.Shape::remove);
        Main.getInstance().getShapes().clear();
        for (String shapeLine : shapes) {
            shapeLine = shapeLine.replace("{", "");
            shapeLine = shapeLine.replace("}", "");
            String[] args = shapeLine.split(", ");
            if (shapeLine.equals(shapes.get(0))) {
                Sketch.getInstance().setMainWidth(Double.valueOf(args[0]));
                Sketch.getInstance().setMainHeight(Double.valueOf(args[1]));
            } else {
                for (String arg : args) {
                    String[] property = arg.split("=");
                    properties.put(property[0], property[1]);
                }
                loadShape(properties);
                properties.clear();
            }
        }
        Main.getInstance().getPrimaryStage().setTitle("Paint - " + loadFile.getName());
        Main.getInstance().getShapes().forEach(model.Shape::draw);
    }

    private static File getSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As..");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paint File(.PNT)", "*.pnt"));
        return fileChooser.showSaveDialog(Main.getInstance().getPrimaryStage());
    }

    private static File getOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paint File(.PNT)", "*.pnt"));
        return fileChooser.showOpenDialog(Main.getInstance().getPrimaryStage());
    }

    private static void loadShape(Map<String, String> properties) {
        Stack<Shape> shapes = Main.getInstance().getShapes();
        Shape s;
        switch (ShapeType.valueOf(properties.get("type"))) {
            case LINE:
                double[] lineStartPoint = {Double.valueOf(properties.get("starting-x")), Double.valueOf(properties.get("starting-y"))};
                double[] lineEndPoint = {Double.valueOf(properties.get("ending-x")), Double.valueOf(properties.get("ending-y"))};
                s = new Line(lineStartPoint, lineEndPoint, Color.web(properties.get("stroke-color")), Double.valueOf(properties.get("stroke-width")));
                if (shapes.search(s) == -1) {
                    shapes.push(s);
                }
                break;
            case RECTANGLE:
                double[] rectStartPoint = {Double.valueOf(properties.get("starting-x")), Double.valueOf(properties.get("starting-y"))};
                double[] rectEndPoint = {Double.valueOf(properties.get("ending-x")), Double.valueOf(properties.get("ending-y"))};
                s = new Rectangle(rectStartPoint, rectEndPoint, Color.web(properties.get("stroke-color")), Color.web(properties.get("fill-color")), Double.valueOf(properties.get("stroke-width")));
                if (shapes.search(s) == -1) {
                    shapes.push(s);
                }
                break;
            case ELLIPSE:
                double[] ellipseStartPoint = {Double.valueOf(properties.get("starting-x")), Double.valueOf(properties.get("starting-y"))};
                double[] ellipseEndPoint = {Double.valueOf(properties.get("ending-x")), Double.valueOf(properties.get("ending-y"))};
                s = new Ellipse(ellipseStartPoint, ellipseEndPoint, Color.web(properties.get("stroke-color")), Color.web(properties.get("fill-color")), Double.valueOf(properties.get("stroke-width")));
                if (shapes.search(s) == -1) {
                    shapes.push(s);
                }
                break;
            case CIRCLE:
                double[] circleStartPoint = {Double.valueOf(properties.get("starting-x")), Double.valueOf(properties.get("starting-y"))};
                double[] circleEndPoint = {Double.valueOf(properties.get("ending-x")), Double.valueOf(properties.get("ending-y"))};
                s = new Circle(circleStartPoint, circleEndPoint, Color.web(properties.get("stroke-color")), Color.web(properties.get("fill-color")), Double.valueOf(properties.get("stroke-width")));
                if (shapes.search(s) == -1) {
                    shapes.push(s);
                }
                break;
            case TRIANGLE:
                double[] xPoints = {Double.valueOf(properties.get("point1-x")), Double.valueOf(properties.get("point2-x")), Double.valueOf(properties.get("point3-x"))};
                double[] yPoints = {Double.valueOf(properties.get("point1-y")), Double.valueOf(properties.get("point2-y")), Double.valueOf(properties.get("point3-y"))};
                s = new Triangle(xPoints, yPoints, Color.web(properties.get("stroke-color")), Color.web(properties.get("fill-color")), Double.valueOf(properties.get("stroke-width")));
                if (shapes.search(s) == -1) {
                    shapes.push(s);
                }
                break;
        }
    }

}
