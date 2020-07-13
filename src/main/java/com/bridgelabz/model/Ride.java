package com.bridgelabz.model;

import com.bridgelabz.utility.RideCategory;

public class Ride {

    public RideCategory rideCategory;
    public int time;
    public double distance;

    /**
     * CONSTRUCTOR to initialize the fields.
     * @param distance
     * @param time
     * @param rideCategory
     */
    public Ride(double distance, int time, RideCategory rideCategory) {
        this.distance = distance;
        this.time = time;
        this.rideCategory = rideCategory;
    }
}