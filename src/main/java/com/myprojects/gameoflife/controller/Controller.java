package com.myprojects.gameoflife.controller;

import com.myprojects.gameoflife.model.GameOfLife;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

    public static String CELL_PANE_STYLE_CLASS = "cell-pane";
    public static String ALIVE_CELL_STYLE_CLASS = "alive";

    private static final double CELL_SIZE = 14;
    private GameOfLife gameOfLife;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ToggleButton playButton;

    @FXML
    private ToggleButton pauseButton;

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


    public Controller(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }



}
