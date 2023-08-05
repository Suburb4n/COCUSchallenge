package org.example.domainmodel;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PeopleJPAAssemblerTest {

    private PeopleJPAAssembler assembler;
    private TripJPA tripJpa;
    private TripId tripId;
    private People one;
    private People two;
    private People three;

    private List<People> peopleList;

    @BeforeEach
    void setUp(){
        assembler = new PeopleJPAAssembler();
        peopleList = new ArrayList<>();
        tripId = new TripId(1L);
        tripJpa = new TripJPA(tripId, "Lx", "Porto", LocalDate.of(2000,01,01)
        ,LocalDate.of(2000,01,10));
        one = new People(new Name("Maria", "Joao"), new TripId(1L));
        two = new People(new Name("José", "Miguel"), new TripId(1L));
        three = new People(new Name("Marta", "Maria"), new TripId(1L));

        peopleList.add(one);
        peopleList.add(two);
        peopleList.add(three);
    }

    @Test
    void listToDataSuccess(){
        //Arrange
        List<PeopleJPA> expected = new ArrayList<>();
        PeopleJPA oneJpa = new PeopleJPA("Maria", "Joao", tripId.getTripId(),tripJpa);
        PeopleJPA twoJpa = new PeopleJPA("José", "Miguel", tripId.getTripId(),tripJpa);
        PeopleJPA threeJpa = new PeopleJPA("Marta", "Maria", tripId.getTripId(),tripJpa);

        expected.add(oneJpa);
        expected.add(twoJpa);
        expected.add(threeJpa);

        //Act
        List<PeopleJPA> result = assembler.listToData(peopleList, tripJpa);
        //Assert
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getTrip(), result.get(0).getTrip());
        for(int i = 0; i<expected.size(); i++){
            assertEquals(expected.get(i).getFirstName(), result.get(i).getFirstName());
            assertEquals(expected.get(i).getLastName(), result.get(i).getLastName());
            assertEquals(expected.get(i).getTripId(), result.get(i).getTripId());
        }

    }

    @Test
    void listToDomain_Success(){
        //Arrange
        List<PeopleJPA> listPeopleJpa = new ArrayList<>();
        PeopleJPA oneJpa = new PeopleJPA("Maria", "Joao", tripId.getTripId(),tripJpa);
        PeopleJPA twoJpa = new PeopleJPA("José", "Miguel", tripId.getTripId(),tripJpa);
        PeopleJPA threeJpa = new PeopleJPA("Marta", "Maria", tripId.getTripId(),tripJpa);

        listPeopleJpa.add(oneJpa);
        listPeopleJpa.add(twoJpa);
        listPeopleJpa.add(threeJpa);
        //Act
        List<People> result = assembler.listToDomain(listPeopleJpa);

        //Assert
        assertEquals(peopleList.size(), result.size());
        for(int i = 0; i<peopleList.size(); i++){
            assertEquals(peopleList.get(i).getName(), result.get(i).getName());
            assertEquals(peopleList.get(i).getTripId(), result.get(i).getTripId());
        }

    }

}