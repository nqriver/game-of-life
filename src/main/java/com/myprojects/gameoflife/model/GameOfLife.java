package com.myprojects.gameoflife.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


import java.util.Random;

import static java.util.Objects.requireNonNull;

public class GameOfLife {

    private static final Random RANDOM = new Random();
    private final ObjectProperty<GenerationSpeed> speed = new SimpleObjectProperty<>(GenerationSpeed.SLOW);
    private final ReadOnlyLongWrapper generation = new ReadOnlyLongWrapper();
    private final Grid grid;
    private Timeline timeline;

    public GameOfLife(Grid grid) {
        this.grid = grid;
        updateTimeline();
        addSpeedPropertyListener();
        grid.generateRandomly(RANDOM);
    }

    private void addSpeedPropertyListener() {
        speed.addListener((observable, oldVal, newVal) -> {
            boolean shouldPlay = timeline.getStatus() == Animation.Status.RUNNING;
            timeline.pause();
            updateTimeline();
            if (shouldPlay) {
                timeline.play();
            }
        });
    }

    private void updateTimeline() {
        Duration duration = new Duration(speed.get().getMillisecondsBetweenGenerations());

        EventHandler<ActionEvent> eventHandler = actionEvent -> {
            grid.nextGeneration();
            generation.set(generation.get() + 1);
        };

        KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public long getGeneration() {
        return generation.get();
    }

    public Grid getGrid() {
        return grid;
    }

    public ReadOnlyLongWrapper generationProperty() {
        return generation;
    }

    public void setSpeed(GenerationSpeed speed) {
        this.speed.set(requireNonNull(speed, "Speed is null"));
    }

    public void clear() {
        timeline.pause();
        grid.clear();
        generation.set(0);
    }

    public void reset() {
        clear();
        grid.generateRandomly(RANDOM);
    }
}
