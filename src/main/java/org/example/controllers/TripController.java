package org.example.controllers;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.Date;
import org.example.dto.NewTripDTO;
import org.example.services.AddPeopleService;
import org.example.services.CreateTripService;
import org.example.services.DeleteTripService;
import org.example.services.ListTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/Trips")
public class TripController {

    private final CreateTripService createTripService;

    private final AddPeopleService addPeopleService;

    private final ListTripsService listTripsService;

    private final DeleteTripService deleteService;

    @Autowired
    public TripController(CreateTripService createTripService, AddPeopleService addPeopleService,
                          ListTripsService listTripsService, DeleteTripService deleteService) {
        this.createTripService = createTripService;
        this.addPeopleService = addPeopleService;
        this.listTripsService = listTripsService;
        this.deleteService = deleteService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createTrip(@RequestBody NewTripDTO tripDTO){

        Date date = new Date(tripDTO.departure, tripDTO.departure);
        NewTripDTO newTripDto = createTripService.createNewTrip(tripDTO.tripId, tripDTO.origCity, tripDTO.destCity, date);
        return new ResponseEntity<>(newTripDto, HttpStatus.OK);
    }
}
