package com.example.cabinvoice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceGeneratorMultipleRidesTest {

    @Test
    public void givenMultipleRides_ShouldReturnTotalAggregateFare() {
        InvoiceGenerator generator = new InvoiceGenerator();

        Ride[] rides = {
            new Ride(2.0, 5),   // fare = 25
            new Ride(0.1, 1)    // fare = min 5
        };

        double totalFare = generator.calculateFare(rides);

        assertEquals(30, totalFare, 0.0);
    }
}
