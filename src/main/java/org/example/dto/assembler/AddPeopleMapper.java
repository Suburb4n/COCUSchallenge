package org.example.dto.assembler;

import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddPeopleDTO;
import org.springframework.stereotype.Component;

@Component
public class AddPeopleMapper {


    public AddPeopleDTO toDto(TripId tripId, People people) {
            AddPeopleDTO dto = new AddPeopleDTO();
            dto.firstName = people.getName().getFirstName();
            dto.lastName = people.getName().getLastName();
            dto.tripId = tripId.getTripId();

        return dto;
    }
}
