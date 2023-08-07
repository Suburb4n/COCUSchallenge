package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactoryInt;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.example.dto.assembler.NewTripMapper;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService {

    private TripRepositoryInt repository;

    private TripFactoryInt factory;

    private final NewTripMapper assembler;

    @Autowired
    public CreateTripService(TripRepositoryInt repository, TripFactoryInt factory, NewTripMapper assembler) {
        this.repository = repository;
        this.factory = factory;
        this.assembler = assembler;
    }

    /**
     * Creates a new trip and saves it to a JPA repository.
     *
     * This method takes the tripId, origin city, destination city, and travel duration as input
     * parameters to create a new trip. It uses the factory to create a Trip object with the provided
     * details. The newly created trip is then saved to the repository using the `save` method.
     *
     * @param tripId The unique identifier for the new trip.
     * @param origCity The origin city of the trip.
     * @param destCity The destination city of the trip.
     * @param date The travel duration for the trip, containing a departure date and an arrival date
     * @return A NewTripDTO representing the newly created trip.
     */
    public NewTripDTO createNewTrip(TripId tripId, City origCity,
                                    City destCity, TravelDuration date){
        Trip newTrip = factory.createTrip(tripId, origCity, destCity,date);

        repository.save(newTrip);
        return assembler.toDto(newTrip);
    }
}
