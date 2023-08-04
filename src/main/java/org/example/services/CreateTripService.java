package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactoryInt;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTripService {

    private TripRepositoryInt repository;

    private TripFactoryInt factory;

    @Autowired
    public CreateTripService(TripRepositoryInt repository, TripFactoryInt factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public Trip createNewTrip(TripId tripId, City origCity,
                              City destCity, Date date){
        Trip newTrip = factory.createTrip(tripId, origCity, destCity,date);
        repository.save(newTrip);
        return newTrip;
    }
}
