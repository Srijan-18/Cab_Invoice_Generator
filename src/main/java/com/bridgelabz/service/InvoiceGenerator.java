package com.bridgelabz.service;

import com.bridgelabz.model.InvoiceSummary;
import com.bridgelabz.model.Ride;

import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {

    private Map<String, InvoiceSummary> invoiceSummaryMap;

    public InvoiceGenerator() {
        this.invoiceSummaryMap = new HashMap<>();
    }

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

    /**
     * TASK: to load User specific Invoice in the Map with keys as UserIDs
     * @param userID
     * @param userRides
     */
    public void setUserSpecificInvoice(String userID, Ride... userRides) {
        invoiceSummaryMap.put(userID, this.getInvoiceSummary(userRides));
    }

    /**
     * TASK: To return Invoice Summary of given UserID.
     * @param userID
     * @return Invoice Summary
     */
    public InvoiceSummary getUserInvoiceSummary(String userID) {
        return invoiceSummaryMap.get(userID);
    }
}