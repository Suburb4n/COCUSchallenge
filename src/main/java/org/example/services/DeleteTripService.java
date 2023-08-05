package org.example.services;

import org.example.domain.valueobjects.TripId;
import org.example.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTripService {

    private TripRepository repository;

    @Autowired
    public DeleteTripService(TripRepository repository) {
        this.repository = repository;
    }

    public boolean deleteTripById(TripId tripId){
        return repository.deleteByTripId(tripId);
    }
}
