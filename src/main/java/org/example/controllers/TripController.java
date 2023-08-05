package org.example.controllers;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.AddPeopleDTO;
import org.example.dto.NewTripDTO;
import org.example.dto.PeopleDTO;
import org.example.services.AddPeopleService;
import org.example.services.CreateTripService;
import org.example.services.DeleteTripService;
import org.example.services.ListTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Trips")
public class TripController {

    private final CreateTripService createTripService;

    private final ListTripsService listTripsService;

    private final DeleteTripService deleteService;

    private final AddPeopleService addService;

    @Autowired
    public TripController(CreateTripService createTripService,
                          ListTripsService listTripsService, DeleteTripService deleteService,
                          AddPeopleService addService) {
        this.createTripService = createTripService;
        this.listTripsService = listTripsService;
        this.deleteService = deleteService;
        this.addService = addService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createTrip(@RequestBody NewTripDTO tripDTO) {
        try {
            TravelDuration date = new TravelDuration(tripDTO.departure, tripDTO.departure);
            NewTripDTO newTripDto = createTripService.createNewTrip(tripDTO.tripId, tripDTO.origCity, tripDTO.destCity, date);
            return new ResponseEntity<>(newTripDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{tripId}/People")
    public ResponseEntity<Object> addPeopleToTrip(@PathVariable Long tripId, @RequestBody PeopleDTO peopleDto) {
        try {
            TripId newTripId = new TripId(tripId);
            Name name = new Name(peopleDto.firstName, peopleDto.lastName);
            People people = new People(name, newTripId);
            AddPeopleDTO peopleToAdd = addService.addPeopleToTrip(people, newTripId);

            return new ResponseEntity<>(peopleToAdd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
