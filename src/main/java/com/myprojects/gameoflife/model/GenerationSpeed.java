package com.myprojects.gameoflife.model;

public enum GenerationSpeed {
    SLOW(300),
    MEDIUM(150),
    FAST(50);

    private final double millisecondsBetweenGenerations;

    public double getMillisecondsBetweenGenerations() {
        return millisecondsBetweenGenerations;
    }

    GenerationSpeed(double millisecondsBetweenGenerations) {
        this.millisecondsBetweenGenerations = millisecondsBetweenGenerations;
    }
}
