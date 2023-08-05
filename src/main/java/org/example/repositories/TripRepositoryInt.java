package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;

import java.util.List;

public interface TripRepositoryInt {

    Trip findById(TripId tripId);
    Trip save(Trip trip);

    Trip patchTrip(Trip trip);

    boolean deleteByTripId(TripId tripId);

    List<Trip> findAll();
}
