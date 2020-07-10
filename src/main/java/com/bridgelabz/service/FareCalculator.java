package com.bridgelabz.service;

import com.bridgelabz.model.Ride;

public class FareCalculator {

    private static int farePerKilometer;
    private static int farePerMinute;
    private static double minimumFare;

    /**
     * TASK: To Set Fares according to Ride Category
     * @param rideCategory
     */
    private void setFaresAccordingTo(Ride.RideCategory rideCategory) {
        switch (rideCategory) {
            case NORMAL:
                farePerKilometer = 10;
                farePerMinute = 1;
                minimumFare = 5;
                break;
            case PREMIUM:
                farePerKilometer = 15;
                farePerMinute = 2;
                minimumFare = 20;
                break;
        }
    }

    /**
     * TASK: To Generate and return fare based on distance and time passed as arguments.
     * @param distance
     * @param time
     * @param rideCategory
     * @return fare
     */
    private double getFare(double distance, int time, Ride.RideCategory rideCategory) {
        this.setFaresAccordingTo(rideCategory);
        double fare = distance * farePerKilometer + time * farePerMinute;
        return Math.max(minimumFare, fare);
    }

    /**
     * TASK : To generate and return total fare based on details of rides taken.
     *
     * @param rides
     * @return total fare
     */
    public double getTotalFare(Ride... rides) {
            double totalFare = 0.0;
            for (Ride ride:rides) {
            totalFare += this.getFare(ride.distance, ride.time, ride.rideCategory);
            }
            return totalFare;
    }
}