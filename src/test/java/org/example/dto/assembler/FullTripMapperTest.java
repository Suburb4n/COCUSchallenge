package org.example.dto.assembler;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.*;
import org.example.dto.FullTripDTO;
import org.example.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FullTripMapperTest {
    private FullTripMapper mapper;

    private FullTripDTO expected;

    private AddPeopleMapper peopleMapper;
    private Person people;
    private TripId tripId;
    @MockBean
    private Trip one;

    @BeforeEach
    void setUp(){
        peopleMapper = new AddPeopleMapper();
        mapper = new FullTripMapper(peopleMapper);
        expected = new FullTripDTO();
        tripId=new TripId(1L);
        expected.tripId = tripId;
        expected.origCity = new City("Orleans");
        expected.destCity = new City("Miami");
        expected.departure = LocalDate.of(2023, 01, 10);
        expected.arrival = LocalDate.of(2023, 01, 20);
        people = new Person(new Name("Diogo", "Luis"), tripId);

    }

    @Test
    void listToDto_success() {
        //Arrange
        TripId tripId = new TripId(1L);
        List<Trip> tripsList = new ArrayList<>();
        tripsList.add(one);
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(people);
        List<FullTripDTO> expectedList = new ArrayList<>();
        PersonDTO dto = new PersonDTO();
        List<PersonDTO> listDto = new ArrayList<>();
        dto.firstName="Diogo";
        dto.lastName = "Luis";
        dto.tripId = 1L;
        listDto.add(dto);
        expected.people =listDto;

        expectedList.add(expected);
        when(one.getTripId()).thenReturn(tripId);
        when(one.getOrgCity()).thenReturn(new City("Orleans"));
        when(one.getDestCity()).thenReturn(new City("Miami"));
        when(one.getDate()).thenReturn(new TravelDuration(LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 20)));
        when(one.getPeople()).thenReturn(peopleList);

        //Act
        List<FullTripDTO> result = mapper.listToDto(tripsList);
        //Assert
        assertEquals(expectedList.size(), result.size());
        for(int i = 0; i< result.size();i++){
            assertEquals(expectedList.get(i).tripId,result.get(i).tripId);
            assertEquals(expectedList.get(i).departure,result.get(i).departure);
            assertEquals(expectedList.get(i).arrival,result.get(i).arrival);
            assertEquals(expectedList.get(i).origCity,result.get(i).origCity);
            assertEquals(expectedList.get(i).destCity,result.get(i).destCity);
            assertEquals(expectedList.get(i).people.size(), result.get(i).people.size());
        }
    }

}