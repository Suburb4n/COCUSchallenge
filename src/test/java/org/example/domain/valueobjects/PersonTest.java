package org.example.domain.valueobjects;
import org.example.exceptions.InvalidPersonNameException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void checkSame_Valid() {
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(1L));

        Person other = new Person(new Name("Maria", "Domingues"), new TripId(1L));
        //Act
        boolean result = one.checkSame(other);

        //Assert
        assertTrue(result);
    }

    @Test
    void checkSame_Invalid() {
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(1L));
        Person other = new Person(new Name("Maria", "Jose"), new TripId(1L));
        ;
        Person another = new Person(new Name("Maria", "Domingues"), new TripId(2L));
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
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(1L));
        Person other = new Person(new Name("Maria", "Domingues"), new TripId(1L));

        //Act
        Boolean result = one.equals(other);

        assertTrue(result);
    }

    @Test
    void equalsAndHashCode_isSame() {
        //Arrange
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(1L));
        //Act
        boolean result = one.equals(one);
        //Assert
        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void equalsAndHashCode_isNotSame() {
        //Arrange
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(1L));
        Person other = new Person(new Name("Maria", "Domingues"), new TripId(2L));

        //Act
        boolean result = one.equals(other);

        //Assert
        assertFalse(result);
        assertNotEquals(one.hashCode(), other.hashCode());
    }

    @Test
    void isDiffObjectType() {
        //Arrange
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(2L));
        Name name = new Name("Not", "Same");

        //Act
        boolean result = one.equals(name);
        //Assert
        assertFalse(result);
    }

    @Test
    void isNullObject() {
        //Arrange
        Person one = new Person(new Name("Maria", "Domingues"), new TripId(2L));
        Person other = null;
        //Act
        boolean result = one.equals(other);
        //Assert
        assertFalse(result);
    }

    @Test
    void EmptyConsctructor(){
        Person people = new Person();

        assertNull(people.getTripId());
        assertNull(people.getName());
    }

    @Test
    public void testInvalidPersonName() {
        // Arrange
        Name name = new Name("", "");
        TripId tripId = new TripId(1L);

        // Act
        InvalidPersonNameException e = assertThrows(InvalidPersonNameException.class, () -> new Person(name, tripId));
        List<IllegalArgumentException> list = e.getExceptions();
        // Assert
        assertEquals(2, list.size());
    }

    @Test
    public void testInvalidPersonMultipleNames() {
        // Arrange
        Name name = new Name("", "Doe");
        TripId tripId = new TripId(1L);


        // Act
        InvalidPersonNameException e = assertThrows(InvalidPersonNameException.class, () -> new Person(name, tripId));
        List<IllegalArgumentException> list = e.getExceptions();


        assertEquals(1, list.size());
    }

}
