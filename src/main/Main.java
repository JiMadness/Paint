package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Main instance;
    private Stage primaryStage;
    private BorderPane root;
    private Node canvas;

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
