package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.example.domainmodel.TripJPAAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
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
        Exception e = assertThrows(IllegalArgumentException.class,()->{
            repository.save(trip);},message ) ;

    }

    @Test
    void deleteById_Valid() {
        //Arrange
        Long test = 1L;
        when(jpaRepositoryInt.existsById(tripId)).thenReturn(true);
        when(tripId.getTripId()).thenReturn(test);
        //Act
        Boolean result = repository.deleteByTripId(tripId);

        //Assert
        verify(jpaRepositoryInt).deleteByTripId(test);
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

        when(jpaRepositoryInt.findAll()).thenReturn(Collections.emptyList());
        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            repository.findAll();
        });
        //Assert
        assertEquals(expected, e.getMessage());
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
        String expected = "Trip not found!";
        Optional<TripJPA> opt = Optional.of(tripJpa);
        when(jpaRepositoryInt.findById(tripId)).thenReturn(opt);
        when(assembler.toDomain(tripJpa)).thenReturn(trip);
        //Act
        Trip result = repository.findById(tripId);

        //Assert
        assertEquals(trip, result);
    }
}