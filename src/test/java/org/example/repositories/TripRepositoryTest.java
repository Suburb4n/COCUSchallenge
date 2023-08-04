package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TripRepositoryTest {

    private TripRepositoryInt repository;

    @MockBean
    private List<Trip> trips;
    @MockBean
    private Trip newTrip;

    @BeforeEach
    void setUp(){
        this.repository  = new TripRepository(trips);
    }

    @Test
    void saveIsValid(){
        //Arrange
        when(trips.contains(newTrip)).thenReturn(false);
        //Act
        Boolean result = repository.save(newTrip);

        //Assert
        verify(trips).contains(newTrip);
        verify(trips).add(newTrip);
        assertTrue(result);
    }

    @Test
    void saveIsInvalid(){
        //Arrange
        when(trips.contains(newTrip)).thenReturn(true);
        //Act
        Boolean result = repository.save(newTrip);

        //Assert
        verify(trips).contains(newTrip);
        assertFalse(result);
    }

    @Test
    void deleteById_Valid(){
        //Arrange
        when(trips.size()).thenReturn(1);
        when(trips.get(0)).thenReturn(newTrip);
        when(newTrip.checkSame(newTrip)).thenReturn(true);

        //Act
        Boolean result = repository.deleteById(newTrip);

        //Assert
        verify(trips).remove(0);
        assertTrue(result);
    }

    @Test
    void deleteById_IdNotFound(){
        //Arrange
        when(trips.size()).thenReturn(1);
        when(trips.get(0)).thenReturn(newTrip);
        when(newTrip.checkSame(newTrip)).thenReturn(false);

        //Act
        Boolean result = repository.deleteById(newTrip);

        //Assert
        assertFalse(result);
    }

    @Test
    void findAllEmpty(){
        String expected = "List is Empty";
        //Act
        Exception e = assertThrows(NullPointerException.class, ()->{
            repository.findAll();
        });
        //Assert
        assertEquals(expected, e.getMessage());
    }
}