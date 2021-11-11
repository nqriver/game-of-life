package com.myprojects.gameoflife.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    private int countAliveNeighbours(int rowIndex, int colIndex) {
        return (int) getNeighbours(rowIndex, colIndex).stream().filter(Cell::isAlive).count();
    }


    private boolean findNextStateOfCell(int rowIndex, int colIndex) {
        int aliveNeighboursCount = countAliveNeighbours(rowIndex, colIndex);
        return ((getCell(rowIndex, colIndex).isAlive() && aliveNeighboursCount == 2) ||
                (aliveNeighboursCount == 3));
    }


    private boolean[][] findNextState() {
        boolean[][] nextState = new boolean[getRowsNumber()][getColsNumber()];
        for (int rowIndex = 0; rowIndex < getRowsNumber(); rowIndex++) {
            for (int colIndex = 0; colIndex < getRowsNumber(); colIndex++) {
                nextState[rowIndex][colIndex] = findNextStateOfCell(rowIndex, colIndex);
            }
        }
        return nextState;
    }

    private void updateToNextState(boolean[][] nextState) {
        for (int rowIndex = 0; rowIndex < getRowsNumber(); rowIndex++) {
            for (int colIndex = 0; colIndex < getRowsNumber(); colIndex++) {
                cells[rowIndex][colIndex].setAlive(nextState[rowIndex][colIndex]);
            }
        }
    }

    public void generateRandomly(Random random) {
        Objects.requireNonNull(random, "Random object is null");
        for (int rowIndex = 0; rowIndex < getRowsNumber(); rowIndex++) {
            for (int colIndex = 0; colIndex < getRowsNumber(); colIndex++) {
                cells[rowIndex][colIndex].setAlive(random.nextBoolean());
            }
        }
    }
}
