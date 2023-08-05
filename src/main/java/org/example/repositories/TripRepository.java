package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.example.domainmodel.TripJPAAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class TripRepository implements TripRepositoryInt {

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
            throw new IllegalArgumentException("Trip not found!");
        }
        return assembler.toDomain(tripJpa.get());
    }

    @Override
    public Trip save(Trip trip) {
        if (jpaRepository.existsById(trip.getTripId())) {
            throw new IllegalArgumentException("Trip already exists!") {
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
    public boolean deleteById(Trip trip) {
        boolean isDeleted = false;
        TripId tripId = trip.getTripId();
        if (jpaRepository.existsById(tripId)) {
            jpaRepository.deleteByTripId(trip.getTripId().getTripId());
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<Trip> findAll() {
        Iterable<TripJPA> listFound = jpaRepository.findAll();
        if(!listFound.iterator().hasNext()){
            throw new IllegalArgumentException("No Trips saved.");
        }
        List<TripJPA> listJpa = new ArrayList<>();
        listFound.forEach(listJpa::add);
        return assembler.listToDomain(listJpa);
    }
}
