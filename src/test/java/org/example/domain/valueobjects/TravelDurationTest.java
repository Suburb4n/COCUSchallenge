package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TravelDurationTest {

    @Test
    void noArgsConstructor(){
        TravelDuration date= new TravelDuration();

        assertNull(date.getDeparture());
        assertNull(date.getArrival());
    }

    @Test
    void testEqualsHashCode_isSame(){
        TravelDuration one = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));

        boolean result = one.equals(one);

        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void testEqualsHashCode_isEqual(){
        TravelDuration one = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        TravelDuration two = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        boolean result = one.equals(two);

        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_departureNotEqual(){
        TravelDuration one = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        TravelDuration two = new TravelDuration(LocalDate.of(1999,01,01),
                LocalDate.of(2000,01,01));
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_arrivalNotEqual(){
        TravelDuration one = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        TravelDuration two = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2001,01,01));
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEquals_isNull(){
        TravelDuration one = new TravelDuration(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        TravelDuration two = null;
        boolean result = one.equals(two);

        assertFalse(result);
    }

    @Test
    public void testValidTravelDuration() {
        // Arrange
        LocalDate departure = LocalDate.of(2000,01,03);
        LocalDate arrival = LocalDate.of(2000,01,010);

        // Act
        TravelDuration travelDuration = new TravelDuration(departure, arrival);

        // Assert
        assertNotNull(travelDuration);
        assertEquals(departure, travelDuration.getDeparture());
        assertEquals(arrival, travelDuration.getArrival());
        assertEquals(0, travelDuration.getExceptions().size());
    }

    @Test
    public void testNullDepartureAndArrival() {
        // Act
        TravelDuration duration = new TravelDuration(null, null);
        //Assert
        assertEquals(1,duration.getExceptions().size());
        assertEquals("Please insert dates",
                duration.getExceptions().get(0).getMessage());
        assertNull(duration.getDeparture());
        assertNull(duration.getArrival());
    }

    @Test
    public void testInvalidDates() {
        // Arrange
        LocalDate departure = LocalDate.of(2000,01,03);
        LocalDate arrival = LocalDate.of(2000,01,02);

        // Act
        TravelDuration duration = new TravelDuration(departure, arrival);
        // Assert
        assertEquals(1,duration.getExceptions().size());
        assertEquals("Please select a departure date before an arrival date.",
                duration.getExceptions().get(0).getMessage());
        assertEquals(departure,duration.getDeparture());
        assertEquals(arrival,duration.getArrival());
    }

    @Test
    public void testInvalidDates_borderConditions() {
        // Arrange
        LocalDate departure = LocalDate.of(2000,01,03);
        LocalDate arrival = LocalDate.of(2000,01,02); // Invalid arrival date

        // Act
        TravelDuration duration = new TravelDuration(departure, arrival);
        // Assert
        assertEquals(1,duration.getExceptions().size());
        assertEquals("Please select a departure date before an arrival date.",
                duration.getExceptions().get(0).getMessage());
        assertEquals(departure,duration.getDeparture());
        assertEquals(arrival,duration.getArrival());
    }

}
