package org.example.services;

import org.example.domain.valueobjects.People;
import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddedPeopleDTO;
import org.example.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddPeopleService {

    private final TripRepository repository;

    @Autowired
    public AddPeopleService(TripRepository repository){
        this.repository =repository;
    }

    public AddedPeopleDTO addPeopleToTrip(List<People> people, TripId tripId){
        Trip tripToAdd = repository.findById(tripId);

        return new AddedPeopleDTO();
    }

}
