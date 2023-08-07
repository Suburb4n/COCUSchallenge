package org.example.domainmodel;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonJPAAssembler {

    /**
     * Converts a list of domain entities (Person) and a TripJPA to add the people to, to a list of data entities (PersonJPA).
     *
     * This method takes a list of Person domain entities and a common associated TripJPA as input and converts each
     * Person domain entity to the corresponding data entity, PersonJPA. It iterates over the list of Person domain entities,
     * and for each Person, it invokes the `toData` method to create the corresponding PersonJPA data entity. The resulting
     * PersonJPA objects are added to a new list, which is then returned as the final result.
     *
     * @param people The list of Person domain entities to be converted to data entities (PersonJPA).
     * @param tripJpa The common associated TripJPA for all the persons in the list.
     * @return A list of PersonJPA objects, each representing the data entity of a person in the list.
     */
    public List<PersonJPA> listToData(List<Person> people, TripJPA tripJpa){
        List<PersonJPA> listJpa = new ArrayList<>();
        for(int i = 0; i<people.size(); i++){
           PersonJPA toAdd = toData(people.get(i), tripJpa);
           listJpa.add(toAdd);
        }
        return listJpa;
    }
    /**
     * Converts a domain entity (Person) and its associated TripJPA to a data entity (PersonJPA).
     *
     * This method takes a Person domain entity and its associated TripJPA as input and converts them to
     * a corresponding data entity, PersonJPA. It extracts the firstName and lastName properties from the
     * Person domain entity's Name object. It also gets the tripId from the Person domain entity's TripId object.
     * Finally, it constructs a new PersonJPA object using the extracted values and the provided TripJPA, representing
     * the data entity of the person, needed to map this entity to the Trip entity in JPA persistence.
     *
     * @param person The Person domain entity to be converted to a data entity (PersonJPA).
     * @param tripJpa The associated TripJPA for the person.
     * @return The PersonJPA object representing the data entity of the person.
     */
    private PersonJPA toData(Person person, TripJPA tripJpa){
        String firstName = person.getName().getFirstName();
        String lastName = person.getName().getLastName();
        Long tripId = person.getTripId().getTripId();

        return new PersonJPA(firstName,lastName,tripId, tripJpa);
    }

    /**
     * Converts a list of data entities (PersonJPA) to a list of domain entities (Person).
     *
     * This method takes a List of PersonJPA objects as input and converts each of them to the corresponding
     * domain entity, Person. It iterates over the list of PersonJPA objects, and for each PersonJPA, it invokes
     * the `toDomain` method to create the corresponding Person domain entity. The resulting Person objects are
     * added to a new list, which is then returned as the final result.
     *
     * @param peopleJpa The list of data entities (PersonJPA) to be converted to domain entities (Person).
     * @return A list of Person objects, each representing the domain model of a person.
     */
    public List<Person> listToDomain(List<PersonJPA> peopleJpa){
        List<Person> people = new ArrayList<>();
        for(int i = 0; i<peopleJpa.size(); i++){
            Person toAdd = toDomain(peopleJpa.get(i));
            people.add(toAdd);
        }
        return people;
    }

    /**
     * Converts a data entity (PersonJPA) to a domain entity (Person).
     *
     * This method takes a PersonJPA object as input and converts it to a corresponding domain entity,
     * Person. It extracts the firstName and lastName properties from the PersonJPA and creates a new
     * Name object with those values. It also creates a TripId object from the tripId property of the
     * PersonJPA. Finally, it constructs a new Person object using the Name and TripId, representing the
     * domain model of the person.
     *
     * @param personJpa The data entity (PersonJPA) to be converted to a domain entity (Person).
     * @return The Person object representing the domain model of the person.
     */
    private Person toDomain(PersonJPA personJpa){
        String firstName = personJpa.getFirstName();
        String lastName = personJpa.getLastName();
        Name name = new Name(firstName, lastName);
        TripId tripId = new TripId(personJpa.getTripId());

        return new Person(name,tripId);
    }
}
