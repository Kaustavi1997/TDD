package cabinvoice.service;

import cabinvoice.utility.UserCategory;

import java.util.HashMap;
import java.util.Map;

public class CabInvoiceGenerator {

    private static final int FARE_PER_KILOMETER = 10 ;
    private static final int FARE_PER_MINUTE = 1 ;
    private static final double MINIMUM_FARE = 5.0;
    private Map<String, InvoiceSummary> invoiceSummaryMap;
    public CabInvoiceGenerator(){
        invoiceSummaryMap = new HashMap<String, InvoiceSummary>();
    }
    public double fareCalculator(double distance, int time, UserCategory category) {
        double fare = distance * FARE_PER_KILOMETER + time * FARE_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    public InvoiceSummary totalFareCalculator(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides ){
            totalFare += this.fareCalculator(ride.distance, ride.time, UserCategory.NORMAL);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void setUserSpecificInvoice(Ride[] userRides, String user, UserCategory premium) {
        invoiceSummaryMap.put(user, this.totalFareCalculator(userRides));
    }

    public InvoiceSummary getUserInvoiceSummary(String userID) {
        return invoiceSummaryMap.get(userID);
    }
}