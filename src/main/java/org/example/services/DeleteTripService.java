package org.example.services;
import org.example.domain.valueobjects.TripId;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteTripService {

    private TripRepositoryInt repository;

    @Autowired
    public DeleteTripService(TripRepositoryInt repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean deleteTripById(TripId tripId){
        return repository.deleteByTripId(tripId);
    }
}
