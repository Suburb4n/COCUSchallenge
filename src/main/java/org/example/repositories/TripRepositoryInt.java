package org.example.repositories;

import org.example.domain.Trip;

public interface TripRepositoryInt {

    Boolean save(Trip trip);

    Boolean deleteById(Trip trip);
}
