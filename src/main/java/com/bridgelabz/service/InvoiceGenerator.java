package com.bridgelabz.service;

import com.bridgelabz.model.Ride;

public class InvoiceGenerator {

    private static final int FARE_PER_KILOMETER = 10;
    private static final int FARE_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    /**
     * TASK: To Generate and return fare based on distance and time passed as arguments.
     * @param distance
     * @param time
     * @return fare
     */
    private double getFare(double distance, int time) {
        double fare = distance * FARE_PER_KILOMETER + time * FARE_PER_MINUTE;
        return Math.max(MINIMUM_FARE, fare);
    }

    /**
     * TASK : To generate and return total fare based on details of rides taken.
     * @param rides
     * @return total fare
     */
    public double getTotalFare(Ride... rides) {
        double totalFare = 0.0;
        for (Ride ride:rides) {
            totalFare += this.getFare(ride.distance, ride.time);
        }
        return totalFare;
    }
}