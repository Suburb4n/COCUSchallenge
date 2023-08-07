package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.example.domainmodel.TripJPAAssembler;
import org.example.exceptions.TripIdAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TripRepositoryTest {

    private TripRepositoryInt repository;
    @MockBean
    TripJPARepositoryInt jpaRepositoryInt;
    @MockBean
    private TripJPAAssembler assembler;
    @MockBean
    private Trip trip;
    @MockBean
    private TripJPA tripJpa;
    @MockBean
    private TripId tripId;
    @MockBean
    private Optional<TripJPA> opt;

    @BeforeEach
    void setUp() {
        this.repository = new TripRepository(jpaRepositoryInt,assembler);
    }

    @Test
    void saveIsValid() {
        //Arrange
        when(jpaRepositoryInt.existsById(tripId)).thenReturn(false);
        when(assembler.toData(trip)).thenReturn(tripJpa);
        when(jpaRepositoryInt.save(tripJpa)).thenReturn(tripJpa);
        //Act
        Trip result = repository.save(trip);

        //Assert
        assertEquals(trip, result);
    }

    @Test
    void saveIsInvalid() {
        //Arrange
        String message = "Trip already exists!";
        when(trip.getTripId()).thenReturn(tripId);
        when(jpaRepositoryInt.existsById(tripId)).thenReturn(true);

        //Act
        Exception e = assertThrows(TripIdAlreadyExistsException.class,()->{
            repository.save(trip);},message ) ;

    }

    @Test
    void patchIsValid() {
        //Arrange

        when(assembler.toData(trip)).thenReturn(tripJpa);
        when(jpaRepositoryInt.save(tripJpa)).thenReturn(tripJpa);
        //Act
        Trip result = repository.save(trip);

        //Assert
        assertEquals(trip, result);
    }


    @Test
    void deleteById_Valid() {
        //Arrange
        TripId id = new TripId(1L);
        when(jpaRepositoryInt.existsById(tripId)).thenReturn(true);
        //Act
        Boolean result = repository.deleteByTripId(tripId);

        //Assert
        verify(jpaRepositoryInt).removeByTripId(any(TripId.class));
        assertTrue(result);
    }

    @Test
    void deleteById_IdNotFound() {
        //Arrange
        when(jpaRepositoryInt.existsById(tripId)).thenReturn(false);
        //Act
        Boolean result = repository.deleteByTripId(tripId);

        //Assert
        assertFalse(result);
    }

    @Test
    void findAllEmpty() {
        String expected = "No Trips saved.";

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            repository.findAll();
        });
        //Assert
        assertEquals(expected, e.getMessage());
    }
    @Test
    void findAllPopulated() {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(trip);
        List<TripJPA> tripJpaList = new ArrayList<>();
        tripJpaList.add(tripJpa);

        when(jpaRepositoryInt.findAll()).thenReturn(tripJpaList);
        when(assembler.listToDomain(tripJpaList)).thenReturn(tripList);
        //Act
        List<Trip> result =repository.findAll();

        //Assert
        assertEquals(tripList, result);
    }


    @Test
    void findById_notFoundEmpty() {
        //Arrange
        String expected = "Trip not found!";
        when(jpaRepositoryInt.findById(tripId)).thenReturn(opt);
        when(opt.isEmpty()).thenReturn(true);
        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            repository.findById(tripId);
        });

        //Assert
        assertEquals(expected, e.getMessage());
    }
    @Test
    void findById_found() {
        //Arrange
        Optional<TripJPA> opt = Optional.of(tripJpa);
        when(jpaRepositoryInt.findById(tripId)).thenReturn(opt);
        when(assembler.toDomain(tripJpa)).thenReturn(trip);
        //Act
        Trip result = repository.findById(tripId);

        //Assert
        assertEquals(trip, result);
    }
}