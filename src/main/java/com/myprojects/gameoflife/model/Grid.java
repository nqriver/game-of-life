package com.myprojects.gameoflife.model;

import java.util.Arrays;
import java.util.List;

import static com.myprojects.gameoflife.model.Utils.requirePositiveInteger;

public class Grid {

    private final int rowsNumber;
    private final int colsNumber;

    private final Cell[][] cells;

    public Grid(int rowsNumber, int colsNumber) {
        this.rowsNumber = requirePositiveInteger(rowsNumber, "Number of rows should be positive");
        this.colsNumber = requirePositiveInteger(colsNumber, "Number of columns should be positive");
        cells = initCells();
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
    }

    private int getWrappedColNumber(int colsNumber) {
        return (colsNumber + rowsNumber) % rowsNumber;
    }


    private int getWrappedRowNumber(int rowNumber) {
        return (rowNumber + rowsNumber) % rowsNumber;
    }

    private Cell getCell(int rowIndex, int colIndex) {
        return cells[getWrappedRowNumber(rowIndex)][getWrappedColNumber(colIndex)];
    }

    private Cell[][] initCells() {
        Cell[][] cells = new Cell[getRowsNumber()][getColsNumber()];
        for (int rowIndex = 0; rowIndex < getRowsNumber(); rowIndex++) {
            for (int colIndex = 0; colIndex < getColsNumber(); colIndex++) {
                cells[rowIndex][colIndex] = new Cell();
            }
        }
        return cells;
    }

    private List<Cell> getNeighbours(int rowIndex, int colIndex) {
        int north = rowIndex - 1;
        int south = rowIndex + 1;
        int east = colIndex + 1;
        int west = colIndex - 1;

        return List.of(
                getCell(north, colIndex),
                getCell(north, east),
                getCell(rowIndex, east),
                getCell(south, east),
                getCell(south, colIndex),
                getCell(south, west),
                getCell(rowIndex, west),
                getCell(north, west)
        );
    }
}
