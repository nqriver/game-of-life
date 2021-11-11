package com.myprojects.gameoflife.model;

public enum GenerationSpeed {
    SLOW(500),
    MEDIUM(300),
    FAST(100);

    private final double millisecondsBetweenGenerations;

    public double getMillisecondsBetweenGenerations() {
        return millisecondsBetweenGenerations;
    }

    GenerationSpeed(double millisecondsBetweenGenerations) {
        this.millisecondsBetweenGenerations = millisecondsBetweenGenerations;
    }
}
