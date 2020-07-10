package cabinvoice.service;

import cabinvoice.utility.RideCategory;

import java.util.HashMap;
import java.util.Map;

public class CabInvoiceGenerator {

    private static int farePerKilometer = 10 ;
    private static int farePerMinuteNormal = 1 ;
    private static double minimumFare = 5.0;
    private Map<String, InvoiceSummary> invoiceSummaryMap;

    /**
     * Initialized the map.
     */
    public CabInvoiceGenerator(){
        invoiceSummaryMap = new HashMap<>();
    }

    /**
     * calculate the fare according to distance,time and ride category
     * @param distance
     * @param time
     * @param category
     * @return
     */

    public double fareCalculator(double distance, int time, RideCategory category) {
        this.fareCategory(category);
        double fare = distance * CabInvoiceGenerator.farePerKilometer + time * farePerMinuteNormal;
        return Math.max(fare, minimumFare);
    }

    /**
     * taking all the parameters according to ride category using switch case.
     * @param category
     */

    private void fareCategory(RideCategory category) {
        switch(category) {
            case NORMAL:
                farePerMinuteNormal = 1;
                farePerKilometer = 10;
                minimumFare = 5.0;
                break;
            case PREMIUM:
                farePerKilometer = 15;
                farePerMinuteNormal = 2;
                minimumFare = 20.0;
                break;
        }
    }

    /**
     * calculation of total fare of the rides.
     * @param rides
     * @return
     */
    public InvoiceSummary totalFareCalculator(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides ){
            totalFare += this.fareCalculator(ride.distance, ride.time, ride.rideCategory);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    /**
     * setter getter method for map
     * @param userRides
     * @param user
     */
    public void setUserSpecificInvoice(Ride[] userRides, String user) {
        invoiceSummaryMap.put(user, this.totalFareCalculator(userRides));
    }

    public InvoiceSummary getUserInvoiceSummary(String userID) {
        return invoiceSummaryMap.get(userID);
    }
}