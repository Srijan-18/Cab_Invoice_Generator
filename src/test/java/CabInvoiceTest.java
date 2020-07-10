import com.bridgelabz.service.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceTest {

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 1;
        Assert.assertEquals(21, invoiceGenerator.getFare(distance, time), 0.0);
    }
}