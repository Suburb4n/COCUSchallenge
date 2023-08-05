package org.example.dto.assembler;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PeopleDTO;
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

    private FullTripDTO toDto(Trip trip) {
        FullTripDTO dto = new FullTripDTO();
        dto.tripId = trip.getTripId();
        dto.origCity = trip.getOrgCity();
        dto.destCity = trip.getDestCity();
        dto.departure = trip.getDate().getDeparture();
        dto.arrival = trip.getDate().getArrival();
        if (!trip.getPeople().isEmpty()) {
            List<PeopleDTO> people = peopleToDto(dto.tripId, trip.getPeople());
            dto.people = people;
        }
        return dto;
    }

    public List<PeopleDTO> peopleToDto(TripId tripId, List<People> people) {
        List<PeopleDTO> listDto = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            PeopleDTO toAdd = peopleMapper.toDto(tripId, people.get(i));
            listDto.add(toAdd);
        }
        return listDto;
    }
}
