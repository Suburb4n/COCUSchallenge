package org.example.domain.People;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {

    @Test
    void checkSame_Valid() {
        People one = new People(new Name("Maria", "Domingues"), new TripId(1L));

        People other = new People(new Name("Maria", "Domingues"), new TripId(1L));
        //Act
        boolean result = one.checkSame(other);

        //Assert
        assertTrue(result);
    }

    @Test
    void checkSame_Invalid() {
        People one = new People(new Name("Maria", "Domingues"), new TripId(1L));
        People other = new People(new Name("Maria", "José"), new TripId(1L));
        ;
        People another = new People(new Name("Maria", "Domingues"), new TripId(2L));
        //Act
        boolean result = one.checkSame(other);
        boolean resultTwo = one.checkSame(another);
        //Assert
        assertFalse(result);
        assertFalse(resultTwo);
    }

    @Test
    void equals_isEqual() {
        //Arrange
        People one = new People(new Name("Maria", "Domingues"), new TripId(1L));
        People other = new People(new Name("Maria", "Domingues"), new TripId(1L));

        //Act
        Boolean result = one.equals(other);

        assertTrue(result);
    }

    @Test
    void equalsAndHashCode_isSame() {
        //Arrange
        People one = new People(new Name("Maria", "Domingues"), new TripId(1L));
        //Act
        boolean result = one.equals(one);
        //Assert
        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void equalsAndHashCode_isNotSame() {
        //Arrange
        People one = new People(new Name("Maria", "Domingues"), new TripId(1L));
        People other = new People(new Name("Maria", "Domingues"), new TripId(2L));

        //Act
        boolean result = one.equals(other);

        //Assert
        assertFalse(result);
        assertNotEquals(one.hashCode(), other.hashCode());
    }

    @Test
    void isDiffObjectType() {
        //Arrange
        People one = new People(new Name("Maria", "Domingues"), new TripId(2L));
        Name name = new Name("Not", "Same");

        //Act
        boolean result = one.equals(name);
        //Assert
        assertFalse(result);
    }

    @Test
    void isNullObject() {
        //Arrange
        People one = new People(new Name("Maria", "Domingues"), new TripId(2L));
        People other = null;
        //Act
        boolean result = one.equals(other);
        //Assert
        assertFalse(result);
    }

}
