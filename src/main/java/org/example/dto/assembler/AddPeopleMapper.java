package org.example.dto.assembler;

import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PeopleDTO;
import org.springframework.stereotype.Component;

@Component
public class AddPeopleMapper {


    public PeopleDTO toDto(TripId tripId, People people) {
            PeopleDTO dto = new PeopleDTO();
            dto.firstName = people.getName().getFirstName();
            dto.lastName = people.getName().getLastName();
            dto.tripId = tripId.getTripId();

        return dto;
    }
}
