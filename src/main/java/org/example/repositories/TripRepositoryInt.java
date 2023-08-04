package org.example.repositories;

import org.example.domain.Trip.Trip;

import java.util.List;

public interface TripRepositoryInt {

    Boolean save(Trip trip);

    Boolean deleteById(Trip trip);

    List<Trip> findAll();
}
