package org.example.dto.assembler;

import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PeopleDTO;
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
    private PeopleDTO expected;
    @MockBean
    private TripId tripId;

    @MockBean
    private People person;

    @BeforeEach
    void setUp() {
        assembler = new AddPeopleMapper();
        expected = new PeopleDTO();
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
        PeopleDTO result = assembler.toDto(tripId, person);
        //Assert
        assertEquals(expected.tripId, result.tripId);
        assertEquals(expected.firstName, result.firstName);
        assertEquals(expected.lastName, result.lastName);
    }

}