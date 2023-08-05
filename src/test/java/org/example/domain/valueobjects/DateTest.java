package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

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

}