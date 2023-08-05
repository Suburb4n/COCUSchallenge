package org.example.services;

import org.example.domain.valueobjects.People;
import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PeopleDTO;
import org.example.dto.assembler.AddPeopleMapper;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPeopleService {

    private final TripRepositoryInt repository;

    private final AddPeopleMapper mapper;

    @Autowired
    public AddPeopleService(TripRepositoryInt repository, AddPeopleMapper mapper){

        this.repository =repository;
        this.mapper = mapper;
    }

    public PeopleDTO addPeopleToTrip(People people, TripId tripId){
        Trip tripToPatch = repository.findById(tripId);
        tripToPatch.addPeople(people);
        repository.patchTrip(tripToPatch);

        return mapper.toDto(tripId, people);
    }

}
