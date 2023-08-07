package org.example.repositories;

import org.example.Main;
import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.example.domainmodel.TripJPAAssembler;
import org.example.exceptions.TripIdAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TripRepository implements TripRepositoryInt {

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    private TripJPARepositoryInt jpaRepository;

    private TripJPAAssembler assembler;

    @Autowired
    public TripRepository(TripJPARepositoryInt jpaRepository, TripJPAAssembler assembler) {
        this.assembler = assembler;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Trip findById(TripId tripId) {
        Optional<TripJPA> tripJpa = jpaRepository.findById(tripId);
        if (tripJpa.isEmpty()) {
            logger.warn("Trip not found!");
            throw new IllegalArgumentException("Trip not found!");
        }
        return assembler.toDomain(tripJpa.get());
    }

    @Override
    public Trip save(Trip trip) {
        if (jpaRepository.existsById(trip.getTripId())) {
            throw new TripIdAlreadyExistsException() {
            };
        }
        TripJPA toSave = assembler.toData(trip);
        jpaRepository.save(toSave);
        return trip;
    }

    @Override
    public Trip patchTrip(Trip trip) {
        TripJPA toSave = assembler.toData(trip);
        jpaRepository.save(toSave);
        return trip;
    }

    @Override
    public boolean deleteByTripId(TripId tripId) {
        boolean isDeleted = false;
        if (jpaRepository.existsById(tripId)) {
            jpaRepository.removeByTripId(tripId);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<Trip> findAll() {
        List<TripJPA> listFound = jpaRepository.findAll();
        if(listFound.isEmpty()){
            throw new IllegalArgumentException("No Trips saved.");
        }

        return assembler.listToDomain(listFound);
    }
}
