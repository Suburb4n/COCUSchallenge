package org.example.dto.assembler;

import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
import org.springframework.stereotype.Component;

@Component
public class AddPeopleMapper {


    public PersonDTO toDto(TripId tripId, Person person) {
            PersonDTO dto = new PersonDTO();
            dto.firstName = person.getName().getFirstName();
            dto.lastName = person.getName().getLastName();
            dto.tripId = tripId.getTripId();

        return dto;
    }
}
