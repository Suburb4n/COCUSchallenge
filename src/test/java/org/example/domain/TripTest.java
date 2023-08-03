package org.example.domain;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void equalsAndHashCode_isEqual(){
        //Arrange
        Trip one = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));

        //Act
        Boolean result = one.equals(other);

        assertTrue(result);
    }

    @Test
    void equalsAndHashCode_isSame(){
        //Arrange
        Trip one = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));

        //Act
        boolean result = one.equals(one);
        //Assert
        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void equalsAndHashCode_isNotSame(){
        //Arrange
        Trip one = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(2L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));

        //Act
        boolean result = one.equals(other);

        //Assert
        assertFalse(result);
        assertNotEquals(one.hashCode(), other.hashCode());
    }

    @Test
    void isDiffObjectType(){
        //Arrange
        Trip one = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Name name = new Name("Not", "Same");

        //Act
        boolean result = one.equals(name);
        //Assert
        assertFalse(result);
    }

    @Test
    void isNullObject(){
        //Arrange
        Trip one = new Trip(new TripId(1L), new ArrayList<>(),
                new City("Lisboa"), new City("Bolonha"), new Date(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = null;
        //Act
        boolean result = one.equals(other);
        //Assert
        assertFalse(result);
    }

}