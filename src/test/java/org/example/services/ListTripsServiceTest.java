package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.dto.FullTripDTO;
import org.example.dto.assembler.FullTripMapper;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ListTripsServiceTest {

    private ListTripsService service;
    @MockBean
    private TripRepository repository;

    @MockBean
    private FullTripMapper mapper;

    @MockBean
    private Trip trip;

    @MockBean
    private FullTripDTO tripDto;

    @BeforeEach
    void setUp(){
        service = new ListTripsService(repository,mapper);
    }
    @Test
    void listAllTrips_PopulatedList() {
        //Arrange
        List<FullTripDTO> dtoList = new ArrayList<>();
        dtoList.add(tripDto);
        List<Trip> tripList = new ArrayList<>();
        tripList.add(trip);
        when(repository.findAll()).thenReturn(tripList);
        when(mapper.listToDto(tripList)).thenReturn(dtoList);
        //Act
        List<FullTripDTO> result = service.listAllTrips();
        //Assert
        assertEquals(dtoList, result);
        assertTrue(result.contains(tripDto));
    }

    @Test
    void listAllTrips_EmptyList() {
        //Arrange
        List<FullTripDTO> dtoList = new ArrayList<>();

        List<Trip> tripList = new ArrayList<>();

        when(repository.findAll()).thenReturn(tripList);
        when(mapper.listToDto(tripList)).thenReturn(dtoList);
        //Act
        List<FullTripDTO> result = service.listAllTrips();
        //Assert
        assertEquals(dtoList, result);
        assertTrue(result.isEmpty());
    }
}