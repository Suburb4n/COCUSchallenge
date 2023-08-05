package org.example.controllers;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PeopleDTO;
import org.example.dto.NewTripDTO;
import org.example.dto.FullTripDTO;
import org.example.services.AddPeopleService;
import org.example.services.CreateTripService;
import org.example.services.DeleteTripService;
import org.example.services.ListTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

            Link link = linkTo(methodOn(TripController.class)
                    .createTrip(tripDTO))
                    .withSelfRel();
            newTripDto.add(link);

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
            PeopleDTO peopleToAdd = addService.addPeopleToTrip(people, newTripId);
            Link link = linkTo(methodOn(TripController.class)
                    .addPeopleToTrip(tripId, peopleDto))
                    .slash(tripId)
                    .withRel("Trip");

            peopleToAdd.add(link);

            return new ResponseEntity<>(peopleToAdd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove={tripId}")
    public ResponseEntity<Object> deleteByTripId(@PathVariable Long tripId) {
        TripId idToDelete = new TripId(tripId);
        boolean operationSuccess = deleteService.deleteTripById(idToDelete);
        if (operationSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Trip not found!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity<Object> listTrips() {
        try {
            List<FullTripDTO> list = listTripsService.listAllTrips();
            for(int i = 0; i<list.size(); i++){
                Link link = linkTo(methodOn(TripController.class)
                        .listTrips())
                        .slash(list.get(i).tripId.getTripId()).
                        withRel("Trip");
                list.get(i).add(link);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
