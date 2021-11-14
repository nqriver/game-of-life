package com.myprojects.gameoflife.controller;

import com.myprojects.gameoflife.model.Cell;
import com.myprojects.gameoflife.model.GameOfLife;
import com.myprojects.gameoflife.model.Grid;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import static java.util.Objects.requireNonNull;

public class Controller {

    public static String CELL_PANE_STYLE_CLASS = "cell-pane";
    public static String ALIVE_CELL_STYLE_CLASS = "alive";

    private static final double CELL_SIZE = 14;
    private GameOfLife gameOfLife;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ToggleButton playToggleButton;

    @FXML
    private ToggleButton pauseToggleButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button clearButton;

    @FXML
    private ToggleButton slowButton;

    @FXML
    private ToggleButton fastButton;

    @FXML
    private ToggleButton middleButton;

    @FXML
    private Label generationNumberLabel;

    @FXML
    private GridPane gridPane;


    public void setGameOfLife(GameOfLife gameOfLife) {
        this.gameOfLife = requireNonNull(gameOfLife, "Game of life is null");
        initGridPane();
    }

    public void initPlayAndPauseToggleGroup() {
        PersistentToggleGroup playAndPause = new PersistentToggleGroup();
        playAndPause.getToggles().addAll(playToggleButton, pauseToggleButton);
        pauseToggleButton.setSelected(true);
    }

    public void initGridPane() {
        Grid grid = gameOfLife.getGrid();
        int numberOfRows = grid.getRowsNumber();
        int numberOfCols = grid.getColsNumber();

        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numberOfCols; colIndex++) {
                addCellPane(rowIndex, colIndex);
            }
        }
    }

    private void addCellPane(int rowIndex, int colIndex) {
        Pane cellPane = new Pane();

        addCellPaneStyle(cellPane);
        addAlivePropertyListener(rowIndex, colIndex, cellPane);
        setAliveStyle(cellPane, gameOfLife.getGrid().getCell(rowIndex, colIndex).isAlive());
        addClickEventHandler(rowIndex, colIndex, cellPane);

        gridPane.add(cellPane, colIndex, rowIndex);

    }

    private void addCellPaneStyle(Pane cellPane) {
        cellPane.setPrefSize(CELL_SIZE, CELL_SIZE);
        GridPane.setFillHeight(cellPane, true);
        GridPane.setFillHeight(cellPane, true);
    }

    private void addAlivePropertyListener(int rowIndex, int colIndex, Pane cellPane) {
        BooleanProperty aliveProperty = gameOfLife.getGrid().getCell(rowIndex, colIndex).aliveProperty();
        aliveProperty.addListener((observableValue, oldVal, newVal) ->
                setAliveStyle(cellPane, newVal));
    }

    private void addClickEventHandler(int rowIndex, int colIndex, Pane cellPane) {
        Cell cell = gameOfLife.getGrid().getCell(rowIndex, colIndex);
        cellPane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> cell.toggleAlive());
    }

    private void setAliveStyle(Pane cellPane, boolean alive) {
        ObservableList<String> styleClass = cellPane.getStyleClass();
        if (alive) {
            styleClass.add(ALIVE_CELL_STYLE_CLASS);
        }
        else {
            styleClass.remove(ALIVE_CELL_STYLE_CLASS);
        }
    }



    public void playToggleButtonAction(ActionEvent actionEvent) {
        gameOfLife.play();

    }

    public void pauseToggleButtonAction(ActionEvent actionEvent) {
        gameOfLife.pause();
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        gameOfLife.reset();
    }

    public void clearButtonAction(ActionEvent actionEvent) {
        gameOfLife.clear();
    }

}
