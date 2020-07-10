package cabinvoice.service;

public class CabInvoiceGenerator {

    private static final int FARE_PER_KILOMETER = 10 ;
    private static final int FARE_PER_MINUTE = 1 ;

    public double fareCalculator(double distance, int time) {
        return distance * FARE_PER_KILOMETER + time * FARE_PER_MINUTE;
    }
}
