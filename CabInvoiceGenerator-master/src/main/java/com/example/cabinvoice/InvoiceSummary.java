package com.example.cabinvoice;

public class InvoiceSummary {

    private int numberOfRides;
    private double totalFare;
    private double averageFare;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
        this.averageFare = totalFare / numberOfRides;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public double getAverageFare() {
        return averageFare;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        InvoiceSummary other = (InvoiceSummary) obj;

        return this.numberOfRides == other.numberOfRides
                && Double.compare(this.totalFare, other.totalFare) == 0
                && Double.compare(this.averageFare, other.averageFare) == 0;
    }
}
