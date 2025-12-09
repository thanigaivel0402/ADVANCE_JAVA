package com.example.cabinvoice;

import java.util.HashMap;
import java.util.Map;

public class RideRepository {

    private Map<String, Ride[]> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        this.userRides.put(userId, rides);
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId);
    }
}
