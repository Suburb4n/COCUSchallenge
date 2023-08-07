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

    /**
     * Creates a new trip based on info stores in NewTripDTo, with tripId,
     * Origin City, destination City and travelDuration.
     *
     * This method takes the details of a new trip in the form of a NewTripDTO object and attempts
     * to create a new trip using the CreateTripService. If the trip creation is successful, the
     * method generates a self-link using the TripController's createTrip method and adds it to the
     * NewTripDTO. The created trip details are then returned in a ResponseEntity with HTTP status
     * code 200 (OK).
     *
     * If the trip creation fails due to an exception, the method logs a warning message and returns
     * a ResponseEntity with the error message and HTTP status code 400 (Bad Request).
     *
     * @param tripDTO The NewTripDTO containing the details of the new trip to be created.
     * @return A ResponseEntity containing the NewTripDTO
     * @throws Exception If an unexpected error occurs during trip creation.
     */
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

    /**
     * Adds a person to an existing trip identified by the provided tripId.
     *
     * This method takes the tripId and the details of the person to be added in the form of a
     * PersonDTO object. It creates a new Person object using the provided details and the tripId.
     * Then, it attempts to add the person to the trip using the AddService. If the addition is
     * successful, a self-link to the trip is generated using the TripController's addPeopleToTrip
     * method and added to the PersonDTO. The PersonDTO with the updated details and the trip link
     * is returned in a ResponseEntity with HTTP status code 200 (OK).
     *
     * If the addition fails due to an exception, the method logs a warning message and returns a
     * ResponseEntity with HTTP status code 400 (Bad Request).
     *
     * @param tripId The unique identifier of the trip to which the person will be added.
     * @param peopleDto The PersonDTO object containing the first and last name of the person to be added.
     * @return A ResponseEntity containing the PersonDTO with the updated details and a link to the trip,
     *         or a ResponseEntity with HTTP status code 400 (Bad Request) if the addition fails.
     * @throws Exception If an unexpected error occurs during the addition of the person to the trip.
     */
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


    /**
     * Retrieves a list of all stored trips.
     *
     * This method calls the ListTripsService to fetch a list of all available trips represented
     * as FullTripDTO objects. It then iterates through the list and generates a self-link for each
     * trip using the TripController's listTrips method and the tripId. The self-links are added to
     * the FullTripDTO objects. The list of FullTripDTO objects, each with a self-link, is returned
     * in a ResponseEntity with HTTP status code 200 (OK).
     *
     * If no trips are found or an error occurs during the retrieval process, the method logs a
     * warning message and returns a ResponseEntity with an error message and HTTP status code 400
     * (Bad Request).
     *
     * @return A ResponseEntity containing a list of FullTripDTO objects, each representing an available trip
     *         with a self-link, or a ResponseEntity with an error message if no trips are found.
     * @throws Exception If an unexpected error occurs during the retrieval of the list of trips.
     */
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
