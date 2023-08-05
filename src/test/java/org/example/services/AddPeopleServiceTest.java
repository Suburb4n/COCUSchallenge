package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddPeopleDTO;
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
    private People people;
    @MockBean
    private Trip trip;

    @MockBean
    private TripId tripId;

    @MockBean
    private AddPeopleDTO peopleDto;

    @BeforeEach
    void setTup(){
        service = new AddPeopleService(repository,mapper);
    }

    @Test
    void addPeopleToTrip(){
        //Arrange
        List<People> list = new ArrayList<>();
        list.add(people);
        when(repository.findById(tripId)).thenReturn(trip);
        when(mapper.toDto(tripId, people)).thenReturn(peopleDto);

        //Act
        AddPeopleDTO result = service.addPeopleToTrip(people,tripId);
        //Assert
        assertEquals(peopleDto, result);
    }
}