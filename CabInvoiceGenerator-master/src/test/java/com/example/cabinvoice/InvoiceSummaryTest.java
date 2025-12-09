package com.example.cabinvoice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceSummaryTest {

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceGenerator generator = new InvoiceGenerator();

        Ride[] rides = {
            new Ride(2.0, 5),  // 25
            new Ride(0.1, 1)   // 5 (minimum)
        };

        InvoiceSummary expectedSummary = new InvoiceSummary(2, 30.0);

        InvoiceSummary actualSummary = generator.calculateInvoiceSummary(rides);

        assertEquals(expectedSummary, actualSummary);
    }
}

