package com.bridgelabz.service;

public class InvoiceGenerator {

    private static final int FARE_PER_KILOMETER = 10;
    private static final int FARE_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    /**
     * TASK: To Generate and return based on distance and time passed as arguments.
     * @param distance
     * @param time
     * @return fare
     */
    public double getFare(double distance, int time) {
        double fare = distance * FARE_PER_KILOMETER + time * FARE_PER_MINUTE;
        return Math.max(MINIMUM_FARE, fare);
    }
}