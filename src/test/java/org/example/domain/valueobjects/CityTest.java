package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testEqualsHashCode_isSame(){
        City one = new City("Miami");

        boolean result = one.equals(one);

        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void testEqualsHashCode_isEqual(){
        City one = new City("Miami");
        City two = new City("Miami");
        boolean result = one.equals(two);

        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_notEqual(){
        City one = new City("Miami");
        City two = new City("Orleans");
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEquals_isNull(){
        City one = new City("Miami");
        City two = null;
        boolean result = one.equals(two);

        assertFalse(result);
    }

}