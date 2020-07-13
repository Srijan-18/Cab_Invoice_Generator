package com.bridgelabz.service;

import com.bridgelabz.model.Ride;
import utility.RideCategory;

public class FareCalculator {

    /**
     * TASK: To Generate and return fare based on distance and time and ride category passed as arguments.
     * @param distance
     * @param time
     * @param rideCategory
     * @return fare
     */
    private double getFare(double distance, int time, RideCategory rideCategory) {
        double fare = distance * rideCategory.farePerKM + time * rideCategory.farePerMinute;
        return Math.max(rideCategory.minimumFare, fare);
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