package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
import org.example.dto.assembler.AddPeopleMapper;
import org.example.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddPeopleServiceTest {

    private AddPeopleService service;

    @MockBean
    private TripRepository repository;
    @MockBean
    private AddPeopleMapper mapper;

    @MockBean
    private Person people;
    @MockBean
    private Trip trip;

    @MockBean
    private TripId tripId;

    @MockBean
    private PersonDTO peopleDto;

    @BeforeEach
    void setTup(){
        service = new AddPeopleService(repository,mapper);
    }

    @Test
    void addPeopleToTrip(){
        //Arrange
        List<Person> list = new ArrayList<>();
        list.add(people);
        when(repository.findById(tripId)).thenReturn(trip);
        when(mapper.toDto(tripId, people)).thenReturn(peopleDto);

        //Act
        PersonDTO result = service.addPeopleToTrip(people,tripId);
        //Assert
        assertEquals(peopleDto, result);
        verify(repository).patchTrip(trip);
    }
}