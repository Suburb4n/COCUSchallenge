package org.example.domain.valueobjects;

import org.junit.jupiter.api.Test;

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

}