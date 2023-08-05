package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactory;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.example.dto.assembler.NewTripDataAssembler;
import org.example.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CreateTripServiceTest {

    private CreateTripService service;

    @MockBean
    private TripRepository repository;

    @MockBean
    private NewTripDataAssembler assembler;
    @MockBean
    private Trip trip;
    @MockBean
    private NewTripDTO dto;
    @MockBean
    private TripFactory factory;
    @MockBean
    private TripId tripId;
    @MockBean
    private City city;
    @MockBean
    private TravelDuration date;

    @BeforeEach
    void setUp(){
        service = new CreateTripService(repository, factory, assembler);
    }

    @Test
    void createNewSerice_isCreated() {
        //Arrange
        when(factory.createTrip(tripId, city,city,date)).thenReturn(trip);
        when(repository.save(trip)).thenReturn(trip);
        when(assembler.toDto(trip)).thenReturn(dto);
        //Act
        NewTripDTO result = service.createNewTrip(tripId, city, city, date);
        //Assert
        assertEquals(dto, result);
        verify(repository).save(trip);
    }

}