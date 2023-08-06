package org.example.domainmodel;

import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactory;
import org.example.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripJPAAssemblerTest {

    private TripJPAAssembler tripAssembler;

    private TripFactory factory;

    private PersonJPAAssembler peopleAssembler;

    private Trip trip;

    private TripJPA tripJpa;
    private TripId tripId;

    private Person one;
    private Person two;
    private Person three;
    private Trip tripWithPeople;
    private TripJPA tripJpaWithPeople;
    private List<PersonJPA> peopleJpaList;

    private List<Person> peopleList;

    @BeforeEach
    void setUp(){
        factory = new TripFactory();
        peopleAssembler = new PersonJPAAssembler();
        tripAssembler = new TripJPAAssembler(factory, peopleAssembler);

        tripId = new TripId(1L);

        trip = factory.createTrip(tripId, new City("Miami"), new City("Los Angeles"),
                new TravelDuration(LocalDate.of(2000,01,01)
                        ,LocalDate.of(2000,01,10)));


        tripJpa = new TripJPA(tripId, "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));

        one = new Person(new Name("Maria", "Joao"), new TripId(1L));
        two = new Person(new Name("José", "Miguel"), new TripId(1L));
        three = new Person(new Name("Marta", "Maria"), new TripId(1L));
        peopleList = new ArrayList<>();
        peopleList.add(one);
        peopleList.add(two);
        peopleList.add(three);

         tripWithPeople = factory.createTrip(tripId, new City("Miami"), new City("Los Angeles"),
                new TravelDuration(LocalDate.of(2000,01,01)
                        ,LocalDate.of(2000,01,10)),
                peopleList);

        tripJpaWithPeople = new TripJPA(tripId, "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));

         peopleJpaList = new ArrayList<>();
        PersonJPA oneJpa = new PersonJPA("Maria", "Joao", tripId.getTripId(),tripJpa);
        PersonJPA twoJpa = new PersonJPA("José", "Miguel", tripId.getTripId(),tripJpa);
        PersonJPA threeJpa = new PersonJPA("Marta", "Maria", tripId.getTripId(),tripJpa);

        peopleJpaList.add(oneJpa);
        peopleJpaList.add(twoJpa);
        peopleJpaList.add(threeJpa);
        tripJpaWithPeople.setPeople(peopleJpaList);
    }

    @Test
    void toData_SuccessEmptyPeopleList(){
        //Arrange

        //Act
        TripJPA result = tripAssembler.toData(trip);
        //Assert
        assertEquals(tripJpa, result);
    }

    @Test
    void toData_SuccessPopulatedPeopleList(){
        //Arrange


        //Act
        TripJPA result = tripAssembler.toData(tripWithPeople);
        //Assert
        assertEquals(tripJpaWithPeople, result);
        assertEquals(tripJpaWithPeople.getPeople().size(), result.getPeople().size());
    }

    @Test
    void toDomain_EmptyPeopleList(){
        //Arrange

        //Act
        Trip result = tripAssembler.toDomain(tripJpa);
        //Assert
        assertEquals(trip, result);
        assertEquals(trip.getPeople().size(), result.getPeople().size());
    }
    @Test
    void toDomain_listIsPopulated(){
        //Arrange

        //Act
        Trip result = tripAssembler.toDomain(tripJpaWithPeople);
        //Assert
        assertEquals(tripWithPeople, result);
        assertEquals(tripJpaWithPeople.getPeople().size(), result.getPeople().size());
    }

    @Test
    void listToDomain_listIsPopulated(){
        //Arrange
        List<TripJPA> tripJpaList = new ArrayList<>();
        tripJpaList.add(tripJpa);
        List<Trip> expected = new ArrayList<>();
        expected.add(trip);
        //Act
        List<Trip> result = tripAssembler.listToDomain(tripJpaList);
        //Assert
        assertEquals(expected.size(), result.size());
        for(int i = 0; i<result.size();i++){
            assertEquals(expected.get(i), result.get(i));
        }
    }

}