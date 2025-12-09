package com.example.cabinvoice;

public class InvoiceGenerator {

    private static final double COST_PER_KM = 10;
    private static final int COST_PER_MIN = 1;
    private static final double MINIMUM_FARE = 5;
    
    private static final double NORMAL_COST_PER_KM = 10;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final double NORMAL_MINIMUM_FARE = 5;

    private static final double PREMIUM_COST_PER_KM = 15;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    public double calculateFare(double distance, int time, RideType rideType) {
        double totalFare = 0;

        switch (rideType) {
            case NORMAL:
                totalFare = distance * NORMAL_COST_PER_KM + time * NORMAL_COST_PER_MIN;
                return Math.max(totalFare, NORMAL_MINIMUM_FARE);

            case PREMIUM:
                totalFare = distance * PREMIUM_COST_PER_KM + time * PREMIUM_COST_PER_MIN;
                return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
        }
        return 0;
    }

    
    public double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MINIMUM_FARE);
    }
    
    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }
    
    public InvoiceSummary calculateInvoiceSummary(Ride[] rides) {
        double totalFare = calculateFare(rides);   // UC-2 method
        return new InvoiceSummary(rides.length, totalFare);
    }

}
