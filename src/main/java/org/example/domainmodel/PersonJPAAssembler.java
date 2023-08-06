package org.example.domainmodel;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonJPAAssembler {

    public List<PersonJPA> listToData(List<Person> people, TripJPA tripJpa){
        List<PersonJPA> listJpa = new ArrayList<>();
        for(int i = 0; i<people.size(); i++){
           PersonJPA toAdd = toData(people.get(i), tripJpa);
           listJpa.add(toAdd);
        }
        return listJpa;
    }

    private PersonJPA toData(Person people, TripJPA tripJpa){
        String firstName = people.getName().getFirstName();
        String lastName = people.getName().getLastName();
        Long tripId = people.getTripId().getTripId();

        return new PersonJPA(firstName,lastName,tripId, tripJpa);
    }

    public List<Person> listToDomain(List<PersonJPA> peopleJpa){
        List<Person> people = new ArrayList<>();
        for(int i = 0; i<peopleJpa.size(); i++){
            Person toAdd = toDomain(peopleJpa.get(i));
            people.add(toAdd);
        }
        return people;
    }

    private Person toDomain(PersonJPA personJpa){
        String firstName = personJpa.getFirstName();
        String lastName = personJpa.getLastName();
        Name name = new Name(firstName, lastName);
        TripId tripId = new TripId(personJpa.getTripId());

        return new Person(name,tripId);
    }
}
