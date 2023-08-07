package org.example.controllers;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.dto.PersonDTO;
import org.example.dto.NewTripDTO;
import org.example.dto.FullTripDTO;
import org.example.services.AddPeopleService;
import org.example.services.CreateTripService;
import org.example.services.DeleteTripService;
import org.example.services.ListTripsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/trips")
public class TripController {
    Logger logger = LoggerFactory.getLogger(TripController.class);
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
            logger.info("Trip was successfully created");
            return new ResponseEntity<>(newTripDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("Trip creation failed, check if trip already exists!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{tripId}/people")
    public ResponseEntity<Object> addPeopleToTrip(@PathVariable Long tripId, @RequestBody PersonDTO peopleDto) {
        try {
            TripId newTripId = new TripId(tripId);
            Name name = new Name(peopleDto.firstName, peopleDto.lastName);
            Person person = new Person(name, newTripId);
            PersonDTO personToAdd = addService.addPeopleToTrip(person, newTripId);
            Link link = linkTo(methodOn(TripController.class)
                    .addPeopleToTrip(tripId, peopleDto))
                    .slash(tripId)
                    .withRel("trip");

            personToAdd.add(link);
            logger.info("Person added to trip");
            return new ResponseEntity<>(personToAdd, HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("Adding failed. Check if people is already on trip!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Object> deleteByTripId(@PathVariable Long tripId) {
        TripId idToDelete = new TripId(tripId);
        boolean operationSuccess = deleteService.deleteTripById(idToDelete);
        logger.info("Trip was deleted!");
        if (operationSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.warn("Deleting failed. Check if trip exists!");
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
                        withRel("trip");
                list.get(i).add(link);
            }
            logger.info("Trips found!");
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("No trips found!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
