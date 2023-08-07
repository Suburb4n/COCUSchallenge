package org.example.domain.Trip;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.example.exceptions.InvalidTripParamsException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class TripTest {


    @Test
    void checkSame_Valid(){
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        //Act
        boolean result = one.sameAs(other);

        //Assert
        assertTrue(result);
    }

    @Test
    void checkSame_Invalid(){
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(2L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        //Act
        boolean result = one.sameAs(other);

        //Assert
        assertFalse(result);
    }

    @Test
    void equals_isEqual(){
        //Arrange
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));

        //Act
        Boolean result = one.equals(other);

        assertTrue(result);
    }

    @Test
    void equalsAndHashCode_isSame(){
        //Arrange
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
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
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = new Trip(new TripId(2L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
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
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
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
        Trip one = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Trip other = null;
        //Act
        boolean result = one.equals(other);
        //Assert
        assertFalse(result);
    }

    @Test
    void addPeopleSuccess(){
        //Arrange
        Trip trip = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Person personOne = new Person(new Name("Joao", "Luis"), new TripId(1l));

        //Act
        trip.addPeople(personOne);

        //Assert
        assertTrue(trip.getPeople().contains(personOne));
    }
    @Test
    void addPeopleAlreadyOnList(){
        //Arrange
        Trip trip = new Trip(new TripId(1L),
                new City("Lisboa"), new City("Bolonha"), new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15)));
        Person personOne = new Person(new Name("Joao", "Luis"), new TripId(1l));
        trip.addPeople(personOne);

        //Assert
        assertThrows(IllegalArgumentException.class,()->{
            trip.addPeople(personOne);
        }, "Person is already on trip");
    }
    @Test
    public void testInvalidTripId() {
        // Arrange
        TripId tripId = new TripId(null);
        City orgCity = new City("Orleans");
        City destCity = new City("New York");
        TravelDuration travelDuration = new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2023,01,15));

        // Act
        InvalidTripParamsException e = assertThrows(InvalidTripParamsException.class,
                ()->{new Trip(tripId, orgCity, destCity, travelDuration);});
        //Assert
        assertEquals(1,e.getExceptions().size());
        assertEquals("Trip Id cannot be null",
                e.getExceptions().get(0).getMessage());
    }

    @Test
    public void testInvalidTravelDuration() {
        // Arrange
        TripId tripId = new TripId(1L);
        City orgCity = new City("Orleans");
        City destCity = new City("New York");
        TravelDuration travelDuration = new TravelDuration(LocalDate.of(2023,01, 10),
                LocalDate.of(2022,01,15));


        // Act
        InvalidTripParamsException e = assertThrows(InvalidTripParamsException.class,
                ()->{new Trip(tripId, orgCity, destCity, travelDuration);});
        //Assert
        assertEquals(1,e.getExceptions().size());
        assertEquals("Please select a departure date before an arrival date.",
                e.getExceptions().get(0).getMessage());
    }

    @Test
    public void testInvalidTrip_BothExceptions() {
        // Arrange
        TripId tripId = new TripId(null);
        City orgCity = new City("Orleans");
        City destCity = new City("New York");
        TravelDuration travelDuration = new TravelDuration(LocalDate.of(2023,01, 10),
                null);

        // Act
        InvalidTripParamsException e = assertThrows(InvalidTripParamsException.class,
                ()->{new Trip(tripId, orgCity, destCity, travelDuration);});
        //Assert

        assertEquals(2,e.getExceptions().size());
        assertEquals("Trip Id cannot be null",
                e.getExceptions().get(0).getMessage());
        assertEquals("Please insert dates",
                e.getExceptions().get(1).getMessage());
    }

}

