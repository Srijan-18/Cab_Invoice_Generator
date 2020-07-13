import com.bridgelabz.exception.CabInvoiceGeneratorException;
import com.bridgelabz.model.InvoiceSummary;
import com.bridgelabz.model.Ride;
import com.bridgelabz.service.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bridgelabz.utility.RideCategory;

public class CabInvoiceTest {

    InvoiceGenerator invoiceGenerator;

    @Before
    public void initializer() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 1;
        Assert.assertEquals(21, invoiceGenerator.getTotalFare(new Ride(distance, time,
                                                                                RideCategory.NORMAL)), 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        Assert.assertEquals(5, invoiceGenerator.getTotalFare(new Ride(0.1, 1,
                                                                                        RideCategory.NORMAL)),
                            0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = { new Ride(2.0, 1, RideCategory.NORMAL),
                         new Ride(0.1, 1, RideCategory.NORMAL)};
        Assert.assertEquals(26, invoiceGenerator.getTotalFare(rides), 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 1, RideCategory.NORMAL),
                        new Ride(0.1, 1, RideCategory.NORMAL)};
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 26);
        Assert.assertEquals(expectedInvoiceSummary, invoiceGenerator.getInvoiceSummary(rides));
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceOfGivenUserId() throws CabInvoiceGeneratorException {
        Ride[] user1Rides = { new Ride(3.0, 2, RideCategory.NORMAL),
                              new Ride(0.1, 1, RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
        Ride []user2Rides = { new Ride(5.0, 2, RideCategory.NORMAL),
                              new Ride(6.1, 3, RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User2", user2Rides);
        Assert.assertEquals(new InvoiceSummary(2,37), invoiceGenerator
                                                                    .getUserInvoiceSummary("User1"));
    }

    @Test
    public void givenRideCategory_SelectivelyPREMIUM_ShouldReturnFareAccordingly() throws CabInvoiceGeneratorException {
        Ride[] user1Rides = { new Ride(3.0, 2, RideCategory.NORMAL),
                              new Ride(0.1, 1, RideCategory.PREMIUM)};
        invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
        Ride []user2Rides = { new Ride(5.0, 2, RideCategory.PREMIUM),
                              new Ride(6.1, 3, RideCategory.NORMAL)};
        invoiceGenerator.setUserSpecificInvoice("User2", user2Rides);
        Assert.assertEquals(new InvoiceSummary(2,52), invoiceGenerator
                                                                    .getUserInvoiceSummary("User1"));
    }

    @Test
    public void givenUserId_WhenDuplicate_ShouldThrowException() {
        try {
            Ride[] user1Rides = {new Ride(3.0, 2, RideCategory.NORMAL),
                    new Ride(0.1, 1, RideCategory.NORMAL)};
            invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
            Ride[] user2Rides = {new Ride(5.0, 2, RideCategory.NORMAL),
                    new Ride(6.1, 3, RideCategory.NORMAL)};
            invoiceGenerator.setUserSpecificInvoice("User1", user2Rides);
        }catch(CabInvoiceGeneratorException e){
            Assert.assertEquals("UserID already exists",e.getMessage());
        }

    }
    @Test
    public void givenUserId_WhenDoesntExists_ShouldThrowException() {
        try {
            Ride[] user1Rides = {new Ride(3.0, 2, RideCategory.NORMAL),
                    new Ride(0.1, 1, RideCategory.NORMAL)};
            invoiceGenerator.setUserSpecificInvoice("User1", user1Rides);
            Ride[] user2Rides = {new Ride(5.0, 2, RideCategory.NORMAL),
                    new Ride(6.1, 3, RideCategory.NORMAL)};
            invoiceGenerator.setUserSpecificInvoice("User2", user2Rides);
            invoiceGenerator.getUserInvoiceSummary("user3");
        }catch(CabInvoiceGeneratorException e){
            Assert.assertEquals(CabInvoiceGeneratorException.ExceptionType.NO_SUCH_KEY,e.e);
        }

    }
    @Test
    public void givenUserId_WhenNoDataPresent_ShouldThrowException()  {
        try {
            invoiceGenerator.getUserInvoiceSummary("user3");
        }catch(CabInvoiceGeneratorException e){
            Assert.assertEquals(CabInvoiceGeneratorException.ExceptionType.EMPTY_MAP,e.e);
        }

    }
}