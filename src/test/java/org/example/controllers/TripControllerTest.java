package org.example.controllers;

import org.example.domain.valueobjects.*;
import org.example.dto.FullTripDTO;
import org.example.dto.PersonDTO;
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
import java.util.List;

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

    private TripId tripId;


    private City city;


    private LocalDate date;
    @MockBean
    private List<FullTripDTO> mockList;
    @MockBean
    private FullTripDTO fullTripDto;

    @MockBean
    private NewTripDTO tripDto;

    @MockBean
    private PersonDTO addedPeopleDto;

    private PersonDTO peopleDto;
    private Long id;
    private Person people;

    @BeforeEach
    void setUp(){
        id= 1L;
        controller = new TripController(createService,
                listService, deleteService, addService);
        date = LocalDate.of(2000,10,10);
        city = new City("Miami");
        tripId = new TripId(id);
        this.tripDTO = new NewTripDTO();
        this.tripDTO.tripId = tripId;
        this.tripDTO.origCity = city;
        this.tripDTO.destCity = city;
        this.tripDTO.departure = date;
        this.tripDTO.arrival = date;
        people = new Person(new Name("Joao", "Luis"), tripId);
        dates = new TravelDuration(tripDTO.departure, tripDTO.arrival);
        peopleDto = new PersonDTO();
        peopleDto.firstName = "Joao";
        peopleDto.lastName = "Luis";
    }

    @Test
    void createTrip_created(){
        //Arrange
        int statusCode = 200;
        when(createService.createNewTrip(tripId, city, city,dates )).thenReturn(tripDto);
        //Act
        ResponseEntity<Object> result = controller.createTrip(tripDTO);
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }
    @Test
    void createTrip_alreadyExists(){
        //Arrange
        int statusCode = 400;
        when(createService.createNewTrip(tripId, city, city,dates )).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<Object> result = controller.createTrip(tripDTO);
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }

    @Test
    void addPeopleToTripSuccess(){
        //Arrange

        int statusCode = 200;
        when(addService.addPeopleToTrip(people, tripId)).thenReturn(addedPeopleDto);
        //Act
        ResponseEntity<Object> result = controller.addPeopleToTrip(id, peopleDto);
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }

    @Test
    void addPeopleToTrip_PeopleAlreadyInTrip(){
        //Arrange
        when(addService.addPeopleToTrip(people, tripId)).thenThrow(IllegalArgumentException.class);

        //Act
        ResponseEntity<Object> result = controller.addPeopleToTrip(id, peopleDto);
        //Assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void deleteTripByTripId_foundAndDeleted(){
        //Arrange

        int statusCode = 200;
        when(deleteService.deleteTripById(tripId)).thenReturn(true);
        //Act
        ResponseEntity<Object> result = controller.deleteByTripId(id);
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }

    @Test
    void deleteTripByTripId_notFound(){
        //Arrange
        String expected = "Trip not found!";
        int statusCode = 400;
        when(deleteService.deleteTripById(tripId)).thenReturn(false);
        //Act
        ResponseEntity<Object> result = controller.deleteByTripId(id);
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
        assertEquals(expected, result.getBody());
    }

    @Test
    void listTrips_Empty(){
        //Arrange
        int statusCode = 400;
        when(listService.listAllTrips()).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<Object> result = controller.listTrips();
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }

    @Test
    void listTrips_Populated(){
        //Arrange
        int statusCode = 200;
        when(listService.listAllTrips()).thenReturn(mockList);
        when(mockList.isEmpty()).thenReturn(false);
        //Act
        ResponseEntity<Object> result = controller.listTrips();
        //Assert
        assertEquals(statusCode, result.getStatusCodeValue());
    }
}