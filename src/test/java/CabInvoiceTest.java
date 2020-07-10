import cabinvoice.service.CabInvoiceGenerator;
import cabinvoice.service.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {

    CabInvoiceGenerator cabInvoiceGenerator;

    @Before
    public void initialiser() {
        cabInvoiceGenerator = new CabInvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
         double distance = 3.0;
        int time = 2;
        double fare = cabInvoiceGenerator.fareCalculator(distance, time);
        Assert.assertEquals(32, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare(){
        double fare = cabInvoiceGenerator.fareCalculator(0.1,1);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = { new Ride(3.0, 2),
                        new Ride(0.1, 1)};
        double totalFare = cabInvoiceGenerator.totalFareCalculator(rides);
        Assert.assertEquals(37, totalFare , 0.0);
    }
}