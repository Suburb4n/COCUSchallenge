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

    public List<FullTripDTO> listAllTrips(){
        List<Trip> trips = repository.findAll();
        return mapper.listToDto(trips);
    }
}
