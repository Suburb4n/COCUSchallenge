package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.dto.FullTripDTO;
import org.example.dto.assembler.FullTripMapper;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTripsService {

    private TripRepositoryInt repository;
    private FullTripMapper mapper;

    @Autowired
    public ListTripsService(TripRepositoryInt repository, FullTripMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retrieves a list of all available trips.
     *
     * This method retrieves all trips from the repository using the `findAll` method. It then
     * maps the list of Trip objects to a list of FullTripDTO objects using the `listToDto` method
     * of the mapper class. The FullTripDTO objects represent the details of each trip, including the people
     * added to it.
     *
     * @return A list of FullTripDTO objects, each representing an available trip. Can return an empty list
     * if trips are not stored.
     */
    public List<FullTripDTO> listAllTrips(){
        List<Trip> trips = repository.findAll();
        return mapper.listToDto(trips);
    }
}
