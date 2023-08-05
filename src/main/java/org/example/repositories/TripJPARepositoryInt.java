package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface TripJPARepositoryInt extends CrudRepository<TripJPA, TripId> {

    Optional<TripJPA> findById(TripId tripId);

    TripJPA save(Trip trip);

    boolean deleteByTripId(Long tripId);

    Iterable<TripJPA> findAll();
}
