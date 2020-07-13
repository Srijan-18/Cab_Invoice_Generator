package com.bridgelabz.service;

import com.bridgelabz.model.Ride;
import com.bridgelabz.utility.RideCategory;

import java.util.Arrays;

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
            double totalFare = Arrays.stream(rides).mapToDouble(ride -> this.getFare
                                                                (ride.distance, ride.time, ride.rideCategory)).sum();
        return totalFare;
    }
}