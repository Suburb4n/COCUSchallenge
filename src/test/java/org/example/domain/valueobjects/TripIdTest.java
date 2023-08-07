package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TripIdTest {

    @Test
    public void noArgsContructor(){
      TripId id = new TripId();
      assertNull(id.getTripId());
    }

    @Test
    public void testValidTripId() {
        // Arrange
        Long validTripId = 1234L;

        // Act
        TripId tripId = new TripId(validTripId);

        // Assert
        assertEquals(validTripId, tripId.getTripId());
        assertEquals(0, tripId.getException().size());
    }

    @Test
    public void testNullTripId() {
        String expected = "Trip Id cannot be null";
        // Act
        TripId tripId = new TripId(null);

        //Assert
        assertEquals(expected, tripId.getException().get(0).getMessage());
        assertEquals(1, tripId.getException().size());
    }

    @Test
    public void testEquals_isEqual(){
        TripId one = new TripId(1L);
        TripId two = new TripId(1L);

        boolean result = one.equals(two);

        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());

    }
    @Test
        public void testEquals_isSame(){
        TripId one = new TripId(1L);


        boolean result = one.equals(one);

        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    public void testEQuals_isDiff(){
        TripId one = new TripId(1L);
        TripId two = new TripId(2L);

        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void testEquals_diffObject(){
        TripId one = new TripId(1L);
        Name two = new Name("Joao", "Luis");

        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }


    @Test
    public void testEquals_null(){
        TripId one = new TripId(1L);
        TripId oneToo = new TripId();
        TripId two = null;

        boolean result = one.equals(two);
        boolean resultToo = one.equals(oneToo);

        assertFalse(result);
        assertFalse(resultToo);
    }
}