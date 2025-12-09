package com.example.cabinvoice;

public class InvoiceService {

    private RideRepository rideRepository;
    private InvoiceGenerator invoiceGenerator;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
        this.invoiceGenerator = new InvoiceGenerator();
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return invoiceGenerator.calculateInvoiceSummary(rides);
    }
}
