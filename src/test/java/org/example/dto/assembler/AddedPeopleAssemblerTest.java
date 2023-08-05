package org.example.dto.assembler;

import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddedPeopleDTO;
import org.example.dto.PeopleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddedPeopleAssemblerTest {

    private AddedPeopleAssembler assembler;
    private AddedPeopleDTO expected;
    @MockBean
    private TripId tripId;
    @MockBean
    private List<People> people;
    @MockBean
    private People person;

    @BeforeEach
    void setUp() {
        assembler = new AddedPeopleAssembler();
        expected = new AddedPeopleDTO();
    }

    @Test
    void toDtoSuccess_EmptyList() {
        //Arrange
        expected.tripId = tripId;
        expected.people = new ArrayList<>();
        when(people.size()).thenReturn(0);
        //Act
        AddedPeopleDTO result = assembler.toDto(tripId, people);
        //Assert
        assertEquals(expected.tripId, result.tripId);
        assertEquals(expected.people.size(), result.people.size());
    }
    @Test
    void toDtoSuccess_PopulatedList() {
        //Arrange
        Name name = new Name("Joao", "Luis");
        PeopleDTO one = new PeopleDTO();
        one.firstName= name.getFirstName();
        one.lastName= name.getLastName();
        PeopleDTO two = new PeopleDTO();
        two.firstName= name.getFirstName();
        two.lastName= name.getLastName();

        expected.tripId = tripId;
        expected.people = new ArrayList<>();
        expected.people.add(one);
        expected.people.add(two);

        when(people.size()).thenReturn(2);
        when(people.get(0)).thenReturn(person);
        when(person.getName()).thenReturn(name);
        when(people.get(1)).thenReturn(person);
        when(person.getName()).thenReturn(name);
        //Act
        AddedPeopleDTO result = assembler.toDto(tripId, people);
        //Assert
        assertEquals(expected.tripId, result.tripId);
        assertEquals(expected.people.size(), result.people.size());
       for(PeopleDTO dto: result.people){
           assertEquals("Joao", dto.firstName);
           assertEquals("Luis", dto.lastName);
       }
    }
}