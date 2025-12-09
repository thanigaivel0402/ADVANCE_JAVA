package com.example.cabinvoice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PremiumRidesTest {

    @Test
    public void givenPremiumRide_ShouldReturnPremiumFare() {
        InvoiceGenerator generator = new InvoiceGenerator();

        Ride ride = new Ride(2.0, 5, RideType.PREMIUM);
        double fare = generator.calculateFare(ride.distance, ride.time, ride.rideType);

        // 2km * 15 + 5min * 2 = 30 + 10 = 40
        assertEquals(40, fare, 0.0);
    }

    @Test
    public void givenMultipleMixedRides_ShouldReturnCorrectTotalFare() {
        InvoiceGenerator generator = new InvoiceGenerator();

        Ride[] rides = {
            new Ride(2.0, 5, RideType.NORMAL),   // 25
            new Ride(2.0, 5, RideType.PREMIUM)   // 40
        };

        double totalFare = generator.calculateFare(rides);
        assertEquals(65, totalFare, 0.0);
    }

    @Test
    public void givenPremiumRideWithLowDistance_ShouldReturnMinimumPremiumFare() {
        InvoiceGenerator generator = new InvoiceGenerator();

        Ride ride = new Ride(0.1, 1, RideType.PREMIUM);

        double fare = generator.calculateFare(ride.distance, ride.time, ride.rideType);

        assertEquals(20, fare, 0.0); // Minimum premium fare = 20
    }
}
