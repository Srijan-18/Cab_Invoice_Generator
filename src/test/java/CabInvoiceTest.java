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
        Assert.assertEquals(21, invoiceGenerator.getFare(distance, time), 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        Assert.assertEquals(5, invoiceGenerator.getFare(0.1, 1), 0.0);
    }
}