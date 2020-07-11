package cabinvoice.service;

public class InvoiceSummary {
    private final int numOfRides;
    private final double totalFare;
    private final double averageFare;


    public InvoiceSummary(int rides, double totalFare) {
        this.numOfRides = rides;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare/this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceSummary)) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numOfRides == that.numOfRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }
}