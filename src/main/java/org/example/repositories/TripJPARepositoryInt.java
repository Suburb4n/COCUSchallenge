package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TripJPARepositoryInt extends CrudRepository<TripJPA, TripId> {

    Optional<TripJPA> findById(TripId tripId);

    TripJPA save(Trip trip);

    Integer removeByTripId(TripId tripId);

    List<TripJPA> findAll();
}
