package main;

import controller.Sketch;
import javafx.stage.FileChooser;
import model.Shape;

import java.io.*;
import java.util.Arrays;

public class DataDealer {
    private static FileReader fileReader;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static File saveFile, loadFile;

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
    }

    public static void load() {
        loadFile = getOpenFile();
        if (loadFile == null) return;
        try {
            fileReader = new FileReader(loadFile);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String[] cmds = reader.readLine().split(", ");
            System.out.println(Arrays.toString(cmds));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
