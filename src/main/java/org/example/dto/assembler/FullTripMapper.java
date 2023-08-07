package org.example.dto.assembler;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
import org.example.dto.FullTripDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FullTripMapper {

    private AddPeopleMapper peopleMapper;

    public FullTripMapper(AddPeopleMapper peopleMapper) {
        this.peopleMapper = peopleMapper;
    }

    public List<FullTripDTO> listToDto(List<Trip> trips) {
        List<FullTripDTO> list = new ArrayList<>();
        for (int i = 0; i < trips.size(); i++) {
            FullTripDTO toAdd = toDto(trips.get(i));
            list.add(toAdd);
        }
        return list;
    }
    /**
     * Converts a domain entity (Trip) to a FullTripDTO representing the full information of a trip.
     *
     * This method takes a Trip domain entity as input and converts it to a corresponding FullTripDTO,
     * which represents the full information of the trip. It extracts the tripId, origin city, destination city,
     * departure, and arrival properties from the Trip domain entity and sets them in the FullTripDTO. If the trip
     * has people participating (not empty people list), it uses the peopleToDto method to convert the list of
     * Person domain entities to a list of PersonDTOs. The resulting PersonDTOs are added to the FullTripDTO's
     * people list.
     *
     * @param trip The Trip domain entity to be converted to a FullTripDTO.
     * @return The FullTripDTO representing the full information of the trip.
     */
    private FullTripDTO toDto(Trip trip) {
        FullTripDTO dto = new FullTripDTO();
        dto.tripId = trip.getTripId();
        dto.origCity = trip.getOrgCity();
        dto.destCity = trip.getDestCity();
        dto.departure = trip.getTravelDuration().getDeparture();
        dto.arrival = trip.getTravelDuration().getArrival();
        if (!trip.getPeople().isEmpty()) {
            List<PersonDTO> person = peopleToDto(dto.tripId, trip.getPeople());
            dto.people = person;
        }
        return dto;
    }

    /**
     * Converts a list of domain entities (Person) to a list of DTOs (PersonDTO) for given specific trip.
     *
     * This method takes a TripId and a list of Person domain entities as input and converts each Person domain entity
     * to the corresponding DTO, PersonDTO, for the specified trip. It iterates over the list of Person domain entities,
     * and for each Person, it invokes the `toDto` method of the PeopleMapper to create the corresponding PersonDTO. The
     * resulting PersonDTO objects are added to a new list, which is then returned as the final result.
     *
     * @param tripId The TripId representing the trip to which the people belong.
     * @param people The list of Person domain entities to be converted to DTOs (PersonDTO) for the specified trip.
     * @return A list of PersonDTO objects, each representing a person participating in the specified trip.
     */
    public List<PersonDTO> peopleToDto(TripId tripId, List<Person> people) {
        List<PersonDTO> listDto = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            PersonDTO personDto = peopleMapper.toDto(tripId, people.get(i));
            listDto.add(personDto);
        }
        return listDto;
    }
}
