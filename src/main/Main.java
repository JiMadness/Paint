package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Shape;

import java.util.Optional;
import java.util.Stack;

public class Main extends Application {

    private static Main instance;
    private Stage primaryStage;
    private BorderPane root;
    private Node canvas;
    private Stack<Shape> shapes = new Stack<>();
    public static void main(String[] args) {
        launch(args);
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setInstance(this);
        this.setPrimaryStage(primaryStage);

        initViews();

        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Paint");
            alert.setHeaderText("Exit Paint");
            alert.setContentText("Are you sure you want to quit Paint?\nUnsaved changes will be lost.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK) {
                e.consume();
            }
        });

        primaryStage.show();
    }

    private void initViews() throws Exception {
        setRoot(FXMLLoader.load(getClass().getResource("../view/frame.fxml")));
        getPrimaryStage().setTitle("Paint");
        getPrimaryStage().setScene(new Scene(getRoot(), 800, 600));
        setCanvas(FXMLLoader.load(getClass().getResource("../view/sketch.fxml")));
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stack<Shape> getShapes() {
        return shapes;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Node getCanvas() {
        return canvas;
    }

    public void setCanvas(Node canvas) {
        this.canvas = canvas;
    }
}
