package cabinvoice.service;

public class CabInvoiceGenerator {

    private static final int FARE_PER_KILOMETER = 10 ;
    private static final int FARE_PER_MINUTE = 1 ;
    private static final double MINIMUM_FARE = 5.0;

    public double fareCalculator(double distance, int time) {
        double fare = distance * FARE_PER_KILOMETER + time * FARE_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    public double totalFareCalculator(Ride[] rides) {
        return 0;
    }
}
