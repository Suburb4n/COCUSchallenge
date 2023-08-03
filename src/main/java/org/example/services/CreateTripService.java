package org.example.services;

import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService {
    @Autowired
    private TripRepositoryInt repository;

    public CreateTripService(TripRepositoryInt repository) {
        this.repository = repository;
    }
}
