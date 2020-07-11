package cabinvoice.service;

import cabinvoice.utility.RideCategory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CabInvoiceGenerator {

    private Map<String, InvoiceSummary> invoiceSummaryMap;

    public CabInvoiceGenerator(){
        invoiceSummaryMap = new HashMap<>();
    }

    public double fareCalculator(double distance, int time, RideCategory category) {
        double fare = distance * category.farePerKM + time * category.farePerMinute;
        return Math.max(fare, category.minimumFare);
    }

    public InvoiceSummary totalFareCalculator(Ride[] rides) {
        double totalFare = Arrays.stream(rides).mapToDouble(ride -> this.fareCalculator
                                                            (ride.distance, ride.time, ride.rideCategory)).sum();
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void setUserSpecificInvoice(Ride[] userRides, String user) {
        invoiceSummaryMap.put(user, this.totalFareCalculator(userRides));
    }

    public InvoiceSummary getUserInvoiceSummary(String userID) {
        return invoiceSummaryMap.get(userID);
    }
}