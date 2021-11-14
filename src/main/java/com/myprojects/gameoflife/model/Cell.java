package com.myprojects.gameoflife.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Cell {

    private final BooleanProperty isAliveProperty = new SimpleBooleanProperty();


    public void setAlive(boolean isAlive) {
        this.isAliveProperty.set(isAlive);
    }

    public void toggleAlive() {
        setAlive(!isAlive());
    }

    public boolean isAlive() {
        return isAliveProperty.get();
    }

    public BooleanProperty aliveProperty() {
        return isAliveProperty;
    }
}
