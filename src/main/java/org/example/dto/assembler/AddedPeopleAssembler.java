package org.example.dto.assembler;

import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddedPeopleDTO;
import org.example.dto.PeopleDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddedPeopleAssembler {

    public AddedPeopleDTO toDto(TripId tripId, List<People> people) {
        AddedPeopleDTO addedPeople = new AddedPeopleDTO();
        List<PeopleDTO> peopleDto = peopleToDto(people);

        addedPeople.tripId = tripId;
        addedPeople.people = peopleDto;
        return addedPeople;
    }

    private List<PeopleDTO> peopleToDto(List<People> people) {
        List<PeopleDTO> peopleDto = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            PeopleDTO dto = new PeopleDTO();
            dto.firstName = people.get(i).getName().getFirstName();
            dto.lastName = people.get(i).getName().getLastName();
            peopleDto.add(dto);
        }
        return peopleDto;
    }
}
