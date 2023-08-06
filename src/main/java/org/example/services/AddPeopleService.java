package org.example.services;

import org.example.domain.valueobjects.Person;
import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
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

    public PersonDTO addPeopleToTrip(Person person, TripId tripId){
        Trip tripToPatch = repository.findById(tripId);
        tripToPatch.addPeople(person);
        repository.patchTrip(tripToPatch);

        return mapper.toDto(tripId, person);
    }

}
