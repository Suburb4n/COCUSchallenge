package org.example.dto;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TripId;

import java.time.LocalDate;
import java.util.List;
import org.springframework.hateoas.RepresentationModel;
public class FullTripDTO extends RepresentationModel<FullTripDTO>{


    public TripId tripId;

    public City origCity;

    public City destCity;

    public LocalDate departure;

    public LocalDate arrival;

    public List<PeopleDTO> people;
}
