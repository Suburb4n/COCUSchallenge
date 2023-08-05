package org.example.domainmodel;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleJPAAssembler {

    public List<PeopleJPA> listToData(List<People> people, TripJPA tripJpa){
        List<PeopleJPA> listJpa = new ArrayList<>();
        for(int i = 0; i<people.size(); i++){
           PeopleJPA toAdd = toData(people.get(i), tripJpa);
           listJpa.add(toAdd);
        }
        return listJpa;
    }

    private PeopleJPA toData(People people, TripJPA tripJpa){
        String firstName = people.getName().getFirstName();
        String lastName = people.getName().getLastName();
        Long tripId = people.getTripId().getTripId();

        return new PeopleJPA(firstName,lastName,tripId, tripJpa);
    }

    public List<People> listToDomain(List<PeopleJPA> people){
        List<People> listJpa = new ArrayList<>();
        for(int i = 0; i<people.size(); i++){
            People toAdd = toDomain(people.get(i));
            listJpa.add(toAdd);
        }
        return listJpa;
    }

    private People toDomain(PeopleJPA people){
        String firstName = people.getFirstName();
        String lastName = people.getLastName();
        Name name = new Name(firstName, lastName);
        TripId tripId = new TripId(people.getTripId());

        return new People(name,tripId);
    }
}
