package org.example.controllers;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.example.services.AddPeopleService;
import org.example.services.CreateTripService;
import org.example.services.DeleteTripService;
import org.example.services.ListTripsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TripControllerTest {

    private TripController controller;

    public NewTripDTO tripDTO;

    private TravelDuration dates;
    @MockBean
    private CreateTripService createService;
    @MockBean
    private ListTripsService listService;
    @MockBean
    private AddPeopleService addService;
    @MockBean
    private DeleteTripService deleteService;

    @MockBean
    private TripId tripId;

    @MockBean
    private City city;

    @MockBean
    private LocalDate date;

    @MockBean
    private NewTripDTO tripDto;

    @BeforeEach
    void setUp(){
        controller = new TripController(createService,
                listService, deleteService);
        this.tripDTO = new NewTripDTO();
        this.tripDTO.tripId = tripId;
        this.tripDTO.origCity = city;
        this.tripDTO.destCity = city;
        this.tripDTO.departure = date;
        this.tripDTO.arrival = date;
        dates = new TravelDuration(tripDTO.departure, tripDTO.arrival);
    }

    @Test
    void createTrip_created(){
        //Arrange
        when(createService.createNewTrip(tripId, city, city,dates )).thenReturn(tripDto);
        //Act
        ResponseEntity<Object> result = controller.createTrip(tripDTO);
        //Assert
        assertEquals(200, result.getStatusCodeValue());
    }
}