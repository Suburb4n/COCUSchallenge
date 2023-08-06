package org.example.dto.assembler;

import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddPeopleMapperTest {

    private AddPeopleMapper assembler;
    private PersonDTO expected;
    @MockBean
    private TripId tripId;

    @MockBean
    private Person person;

    @BeforeEach
    void setUp() {
        assembler = new AddPeopleMapper();
        expected = new PersonDTO();
    }

    @Test
    void toDtoSuccess_EmptyList() {
        //Arrange
        Name name = new Name("Joao", "Luis");
        expected.tripId = tripId.getTripId();
        expected.firstName = "Joao";
        expected.lastName = "Luis";
        when(person.getName()).thenReturn(name);
        //Act
        PersonDTO result = assembler.toDto(tripId, person);
        //Assert
        assertEquals(expected.tripId, result.tripId);
        assertEquals(expected.firstName, result.firstName);
        assertEquals(expected.lastName, result.lastName);
    }

}