package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void noArgsConstructor(){
        Name name= new Name();

        assertNull(name.getFirstName());
        assertNull(name.getLastName());
    }

    @Test
    void constructorArgs(){
        String fn_expected = "Joao";
        String ln_expected = "Luis";
        Name name = new Name("Joao", "Luis");
        assertEquals(fn_expected, name.getFirstName());
        assertEquals(ln_expected, name.getLastName());
    }

    @Test
    void testEqualsHashCode_isSame(){
        Name one = new Name("Maria", "José");

        boolean result = one.equals(one);

        assertTrue(result);
        assertEquals(one.hashCode(), one.hashCode());
    }

    @Test
    void testEqualsHashCode_isEqual(){
        Name one = new Name("Maria", "José");
        Name two = new Name("Maria", "José");
        boolean result = one.equals(two);

        assertTrue(result);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void testEqualsHashCode_notEqual(){
        Name one = new Name("Maria", "José");
        Name two = new Name("Joaquim", "José");
        boolean result = one.equals(two);

        assertFalse(result);
        assertNotEquals(one.hashCode(), two.hashCode());
    }
    @Test
    void testEqualsHashCode_surnameNotEqual(){
        Name one = new Name("Maria", "José");
        Name two = new Name("Maria", "Maria");
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

    @Test
    public void testInvalidFirstName() {
        // Arrange
        String firstName = null;
        String lastName = "Doe";

        // Act
        Name name = new Name(firstName, lastName);

        // Assert
        List<IllegalArgumentException> exceptions = name.getExceptions();
        assertEquals(1, exceptions.size());
        assertEquals("Invalid First Name", exceptions.get(0).getMessage());
    }

    @Test
    public void testInvalidLastName() {
        // Arrange
        String firstName = "John";
        String lastName = "";
        // Act
        Name name = new Name(firstName, lastName);
        // Assert
        List<IllegalArgumentException> exceptions = name.getExceptions();
        assertEquals(1, exceptions.size());
        assertEquals("Invalid Last Name", exceptions.get(0).getMessage());
    }

    @Test
    public void testMultipleInvalidNames() {
        // Arrange
        String firstName = null;
        String lastName = "";

        // Act
        Name name = new Name(firstName, lastName);

        // Assert
        List<IllegalArgumentException> exceptions = name.getExceptions();

        assertEquals(2, exceptions.size());
        assertEquals("Invalid First Name", exceptions.get(0).getMessage());
        assertEquals("Invalid Last Name", exceptions.get(1).getMessage());
    }

}