package com.myprojects.gameoflife.model;

public class Grid {

    private final int rowsNumber;
    private final int colsNumber;

    private final Cell[][] cells;

    public Grid(int rowsNumber, int colsNumber) {
        this.rowsNumber = rowsNumber;
        this.colsNumber = colsNumber;
        cells = initCells();
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColsNumber() {
        return colsNumber;
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
}
