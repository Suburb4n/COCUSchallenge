package org.example.domainmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleJPATest {

    @Test
    void testEmptyConstructor(){
        PeopleJPA peopleJpa = new PeopleJPA();
        assertNull(peopleJpa.getTrip());
        assertNull(peopleJpa.getFirstName());
        assertNull(peopleJpa.getLastName());
        assertNull(peopleJpa.getTrip());
    }
}