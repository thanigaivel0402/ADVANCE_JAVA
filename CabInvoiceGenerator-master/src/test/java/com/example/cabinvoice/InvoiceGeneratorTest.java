package com.example.cabinvoice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceGeneratorTest {

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double fare = generator.calculateFare(2.0, 5);
        assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenShortDistance_ShouldReturnMinimumFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double fare = generator.calculateFare(0.1, 1);
        assertEquals(5, fare, 0.0);
    }
}
