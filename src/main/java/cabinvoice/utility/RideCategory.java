package cabinvoice.utility;

public enum RideCategory {

    PREMIUM (15, 2, 20),
    NORMAL(10, 1, 5);

    public int farePerKM;
    public int farePerMinute;
    public int minimumFare;

    RideCategory(int farePerKM, int farePerMinute, int minimumFare) {
        this.farePerKM = farePerKM;
        this.farePerMinute = farePerMinute;
        this.minimumFare = minimumFare;
    }
}
