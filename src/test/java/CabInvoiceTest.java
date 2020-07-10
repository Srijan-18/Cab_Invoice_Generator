import com.bridgelabz.model.InvoiceSummary;
import com.bridgelabz.model.Ride;
import com.bridgelabz.service.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {

    InvoiceGenerator invoiceGenerator;

    @Before
    public void initializer() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 1;
        Assert.assertEquals(21, invoiceGenerator.getTotalFare(new Ride(distance, time, Ride.RideCategory.NORMAL)), 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        Assert.assertEquals(5, invoiceGenerator.getTotalFare(new Ride(0.1, 1, Ride.RideCategory.NORMAL)),
                            0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = { new Ride(2.0, 1, Ride.RideCategory.NORMAL),
                         new Ride(0.1, 1, Ride.RideCategory.NORMAL)};
        Assert.assertEquals(26, invoiceGenerator.getTotalFare(rides), 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 1, Ride.RideCategory.NORMAL),
                        new Ride(0.1, 1, Ride.RideCategory.NORMAL)};
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 26);
        Assert.assertEquals(expectedInvoiceSummary, invoiceGenerator.getInvoiceSummary(rides));
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceOfGivenUserId() {
        Ride[] user1Rides = { new Ride(3.0, 2, Ride.RideCategory.NORMAL),
                              new Ride(0.1, 1, Ride.RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
        Ride []user2Rides = { new Ride(5.0, 2, Ride.RideCategory.NORMAL),
                              new Ride(6.1, 3, Ride.RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User2", user2Rides);
        Assert.assertEquals(new InvoiceSummary(2,37), invoiceGenerator
                                                                    .getUserInvoiceSummary("User1"));
    }

    @Test
    public void givenRideCategory_SelectivelyPREMIUM_ShouldReturnFareAccordingly() {
        Ride[] user1Rides = { new Ride(3.0, 2, Ride.RideCategory.NORMAL),
                              new Ride(0.1, 1, Ride.RideCategory.PREMIUM)};
        invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
        Ride []user2Rides = { new Ride(5.0, 2, Ride.RideCategory.PREMIUM),
                              new Ride(6.1, 3, Ride.RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User2", user2Rides);
        Assert.assertEquals(new InvoiceSummary(2,52), invoiceGenerator
                                                                    .getUserInvoiceSummary("User1"));
    }
}