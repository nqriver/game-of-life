package com.myprojects.gameoflife;

import com.myprojects.gameoflife.controller.Controller;
import com.myprojects.gameoflife.model.GameOfLife;
import com.myprojects.gameoflife.model.Grid;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * JavaFX App
 */
public class GameOfLifeApp extends Application {

    private static final String APP_NAME = "Game of life";
    private static final String VIEW_PATH = "view.fxml";
    private static final int NUMBER_OF_ROWS = 40;
    private static final int NUMBER_OF_COLS = 70;

    private final GameOfLife gameOfLife;
    private FXMLLoader fxmlLoader;
    private Stage primaryStage;
    private Parent view;


    public GameOfLifeApp() {
        this(new GameOfLife(new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLS)));
    }

    public GameOfLifeApp(GameOfLife gameOfLife) {
        this.gameOfLife = requireNonNull(gameOfLife, "game of life is null");
    }
    @Override
    public void start(Stage stage) throws IOException {
        initializePrimaryStage(stage);
        initializeFxmlLoader();
        initializeView();
        initializeController();
        showScene();
    }

    private void showScene() {
        primaryStage.setScene(new Scene(view));
        primaryStage.show();

    }

    private void initializeController() {
        Controller controller = fxmlLoader.getController();
        controller.setGameOfLife(this.gameOfLife);
    }

    private void initializeView() throws IOException {
        this.view = fxmlLoader.load();
    }

    private void initializeFxmlLoader() {
        this.fxmlLoader = new FXMLLoader();
        this.fxmlLoader.setLocation(GameOfLifeApp.class.getResource(VIEW_PATH));
    }

    private void initializePrimaryStage(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle(APP_NAME);
        this.primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        this.primaryStage.setResizable(false);
        this.primaryStage.sizeToScene();
    }
}