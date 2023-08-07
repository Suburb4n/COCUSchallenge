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
    /**
     * Adds a person to an existing trip identified by the provided tripId.
     *
     * This method takes a Person object and a TripId as input parameters to add the person to
     * the existing trip. It searches for the trip from the repository using the given tripId,and if found
     * then adds the person to the trip using the `addPeople` method of the Trip object. The
     * updated trip is then saved back to the repository using the `patchTrip` method.
     *
     * @param person The Person object, containing a first and last name, representing the person to be added to the trip.
     * @param tripId The unique identifier of the trip to which the person will be added.
     * @return A PersonDTO representing the details of the added person in the context of the trip.
     */
    public PersonDTO addPeopleToTrip(Person person, TripId tripId){
        Trip tripToPatch = repository.findById(tripId);
        tripToPatch.addPeople(person);
        repository.patchTrip(tripToPatch);

        return mapper.toDto(tripId, person);
    }

}
