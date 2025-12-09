package com.example.cabinvoice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceServiceTest {

    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();

        Ride[] rides = {
            new Ride(2.0, 5),  // 25
            new Ride(0.1, 1)   // 5 minimum
        };

        invoiceService.addRides("user1", rides);

        InvoiceSummary summary = invoiceService.getInvoiceSummary("user1");

        InvoiceSummary expected = new InvoiceSummary(2, 30.0);

        assertEquals(expected, summary);
    }
}
