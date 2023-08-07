package org.example.services;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.example.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DeleteTripServiceTest {



        private DeleteTripService service;

        @MockBean
        private TripRepository repository;

        @MockBean
        private Person people;
        @MockBean
        private Trip trip;

        @MockBean
        private TripId tripId;

        @BeforeEach
        void setTup(){
            service = new DeleteTripService(repository);
        }

        @Test
        void deletePeoplefromTrip_success(){
            //Arrange
            List<Person> list = new ArrayList<>();
            list.add(people);
            when(repository.deleteByTripId(tripId)).thenReturn(true);

            //Act
            boolean result = service.deleteTripById(tripId);
            //Assert
            assertTrue(result);
            verify(repository).deleteByTripId(tripId);
        }

    @Test
    void deletePeoplefromTrip_failure(){
        //Arrange
        List<Person> list = new ArrayList<>();
        list.add(people);
        when(repository.deleteByTripId(tripId)).thenReturn(false);

        //Act
        boolean result = service.deleteTripById(tripId);
        //Assert
        assertFalse(result);
        verify(repository).deleteByTripId(tripId);
    }
    }
