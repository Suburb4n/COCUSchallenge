package org.example.domainmodel;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TripJPATest {
    private TripJPA tripJpa;



    @BeforeEach
    void setUp(){
        tripJpa = new TripJPA();
    }

    @Test
    void emptyConstructor(){

        assertNull(tripJpa.getTripId());
        assertNull(tripJpa.getOrigCity());
        assertNull(tripJpa.getDestCity());
        assertNull(tripJpa.getDeparture());
        assertNull(tripJpa.getArrival());
        assertNull(tripJpa.getPeople());
    }

    @Test
    void testEquals_isEquals(){
        TripJPA one = new TripJPA(new TripId(1L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));
        TripJPA two = new TripJPA(new TripId(1L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));

        //Act
        boolean result = one.equals(two);
        //Assert
        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEquals_isSame(){
        TripJPA one = new TripJPA(new TripId(1L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));

        //Act
        boolean result = one.equals(one);
        //Assert
        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void testEquals_isNotSame(){
        TripJPA one = new TripJPA(new TripId(1L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));
        TripJPA two = new TripJPA(new TripId(2L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));

        //Act
        boolean result = one.equals(two);
        //Assert
        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEquals_isDiffObject(){
        TripJPA one = new TripJPA(new TripId(1L), "Miami", "Los Angeles", LocalDate.of(2000,01,01)
                ,LocalDate.of(2000,01,10));
        TripId id = new TripId(2l);
        //Act
        boolean result = one.equals(id);
        //Assert
        assertFalse(result);
        assertNotEquals(one.hashCode(), id.hashCode());
    }

}