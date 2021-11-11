package com.myprojects.gameoflife.model;

public class Utils {

    private Utils() {}

    public static int requirePositiveInteger(int number, String message) {
        if (number <= 0) {
            throw new IllegalArgumentException(message);
        }
        return number;
    }


}
