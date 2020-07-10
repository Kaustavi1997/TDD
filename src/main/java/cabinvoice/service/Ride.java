package cabinvoice.service;

import cabinvoice.utility.RideCategory;

public class Ride {

    public double distance;
    public int time;
    public RideCategory rideCategory;

    public Ride(double distance, int time, RideCategory category) {
        this.distance = distance;
        this.time = time;
        this.rideCategory = category;
    }
}