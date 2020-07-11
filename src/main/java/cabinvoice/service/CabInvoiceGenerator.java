package cabinvoice.service;

import cabinvoice.exception.CabInvoiceGeneratorException;
import cabinvoice.model.InvoiceSummary;
import cabinvoice.model.Ride;
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

    public void setUserSpecificInvoice(Ride[] userRides, String user) throws CabInvoiceGeneratorException {
        if ((invoiceSummaryMap.containsKey(user))){
            throw new CabInvoiceGeneratorException(CabInvoiceGeneratorException.ExceptionType.KEY_ALREADY_EXISTS,"UserID already exists");
        }
        invoiceSummaryMap.put(user, this.totalFareCalculator(userRides));
    }

    private void checkEmpty(Map checkMap) throws CabInvoiceGeneratorException {
        if(checkMap == null || checkMap.size() == 0){
            throw new CabInvoiceGeneratorException(CabInvoiceGeneratorException.ExceptionType.EMPTY_MAP,"No Invoice to display");
        }
    }

    public InvoiceSummary getUserInvoiceSummary(String userID) throws CabInvoiceGeneratorException {
        checkEmpty(invoiceSummaryMap);
        if (!(invoiceSummaryMap.containsKey(userID))){
            throw new CabInvoiceGeneratorException(CabInvoiceGeneratorException.ExceptionType.NO_SUCH_KEY,"No such user Id present");
        }
        return invoiceSummaryMap.get(userID);
    }
}