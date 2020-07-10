package com.bridgelabz.service;

import com.bridgelabz.model.InvoiceSummary;
import com.bridgelabz.model.Ride;

public class InvoiceGenerator {

    /**
     * TASK : To generate and return total fare based on details of rides taken.
     * @param rides
     * @return total fare
     */
    public double getTotalFare(Ride... rides) {
        FareCalculator fareCalculator = new FareCalculator();
        return fareCalculator.getTotalFare(rides);
    }

    /**
     * TASK: to return Invoice summary based on details of rides given.
     * @param rides
     * @return Invoice Summary
     */
    public InvoiceSummary getInvoiceSummary(Ride... rides) {
        return new InvoiceSummary(rides.length, this.getTotalFare(rides));
    }
}