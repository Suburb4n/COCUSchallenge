package org.example.domain.valueobjects;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void noArgsConstructor(){
        Date date= new Date();

        assertNull(date.getDeparture());
        assertNull(date.getArrival());
    }

    @Test
    void testEqualsHashCode_isSame(){
        Date one = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));

        boolean result = one.equals(one);

        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void testEqualsHashCode_isEqual(){
        Date one = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        Date two = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        boolean result = one.equals(two);

        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_departureNotEqual(){
        Date one = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        Date two = new Date(LocalDate.of(1999,01,01),
                LocalDate.of(2000,01,01));
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_arrivalNotEqual(){
        Date one = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        Date two = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2001,01,01));
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEquals_isNull(){
        Date one = new Date(LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01));
        Date two = null;
        boolean result = one.equals(two);

        assertFalse(result);
    }

}