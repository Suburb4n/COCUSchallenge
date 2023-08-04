package org.example.dto.assembler;

import org.example.domain.Trip.Trip;
import org.example.dto.NewTripDTO;
import org.springframework.stereotype.Service;

@Service
public class NewTripDataAssembler {

    public NewTripDTO toDto(Trip trip) {
        NewTripDTO dto = new NewTripDTO();
        dto.tripId = trip.getTripId();
        dto.origCity = trip.getOrgCity();
        dto.destCity = trip.getDestCity();
        dto.departure = trip.getDate().getDeparture();
        dto.arrival = trip.getDate().getArrival();

        return dto;
    }
}
