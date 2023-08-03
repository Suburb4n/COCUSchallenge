package org.example.repositories;

import org.example.domain.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
        TripRepositoryInt repository = new TripRepository(trips);
        this.repository = repository;
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

}