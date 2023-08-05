package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactoryInt;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.example.dto.assembler.NewTripDataAssembler;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService {

    private TripRepositoryInt repository;

    private TripFactoryInt factory;

    private final NewTripDataAssembler assembler;

    @Autowired
    public CreateTripService(TripRepositoryInt repository, TripFactoryInt factory, NewTripDataAssembler assembler) {
        this.repository = repository;
        this.factory = factory;
        this.assembler = assembler;
    }

    public NewTripDTO createNewTrip(TripId tripId, City origCity,
                                    City destCity, TravelDuration date){
        Trip newTrip = factory.createTrip(tripId, origCity, destCity,date);
        repository.save(newTrip);
        return assembler.toDto(newTrip);
    }
}
