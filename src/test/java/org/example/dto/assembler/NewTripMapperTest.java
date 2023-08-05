package org.example.dto.assembler;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NewTripMapperTest {

    private NewTripMapper assembler;

    private NewTripDTO expected;
    @MockBean
    private Trip trip;
    @MockBean
    private TravelDuration date;

    @BeforeEach
    void setUp(){
        assembler = new NewTripMapper();
        expected = new NewTripDTO();
        expected.tripId = new TripId(1L);
        expected.origCity = new City("Orleans");
        expected.destCity = new City("Miami");
        expected.departure = LocalDate.of(2023, 01, 10);
        expected.arrival = LocalDate.of(2023, 01, 20);

    }

    @Test
    void toDTO_isValid(){
        //Arrange
        when(trip.getTripId()).thenReturn(new TripId(1L));
        when(trip.getOrgCity()).thenReturn(new City("Orleans"));
        when(trip.getDestCity()).thenReturn(new City("Miami"));
        when(trip.getDate()).thenReturn(date);
        when(date.getDeparture()).thenReturn(LocalDate.of(2023, 01, 10));
        when(date.getArrival()).thenReturn(LocalDate.of(2023, 01, 20));

        //Act
        NewTripDTO result = assembler.toDto(trip);
        //Assert
        assertEquals(expected.tripId, result.tripId);
        assertEquals(expected.origCity, result.origCity);
        assertEquals(expected.destCity, result.destCity);
        assertEquals(expected.departure, result.departure);
        assertEquals(expected.arrival, result.arrival);

    }

}