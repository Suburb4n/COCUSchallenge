package org.example.dto;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TripId;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class NewTripDTO extends RepresentationModel<NewTripDTO> {

    public TripId tripId;

    public City origCity;

    public City destCity;

    public LocalDate departure;

    public LocalDate arrival;
}
