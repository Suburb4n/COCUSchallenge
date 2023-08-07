package org.example.repositories;

import org.example.Main;
import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.TripId;
import org.example.domainmodel.TripJPA;
import org.example.domainmodel.TripJPAAssembler;
import org.example.exceptions.TripIdAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TripRepository implements TripRepositoryInt {

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    private TripJPARepositoryInt jpaRepository;

    private TripJPAAssembler assembler;

    @Autowired
    public TripRepository(TripJPARepositoryInt jpaRepository, TripJPAAssembler assembler) {
        this.assembler = assembler;
        this.jpaRepository = jpaRepository;
    }

    /**
     * Retrieves a trip by its unique identifier (tripId).
     *
     * This method takes a TripId as input parameter and attempts to find the corresponding trip
     * in the repository using the `findById` method of the JpaRepository. If the trip is found, it is returned as
     * am Object Optional.of(TripJPA).
     * The object TripJPA is then mapped to a Trip object using the assembler's `toDomain` method, representing the
     * domain model of the trip.
     *
     * @param tripId The unique identifier of the trip to be retrieved.
     * @return The Trip object representing the trip with the given tripId.
     * @throws IllegalArgumentException If the trip with the specified tripId is not found in the repository.
     */
    @Override
    public Trip findById(TripId tripId) {
        Optional<TripJPA> tripJpa = jpaRepository.findById(tripId);
        if (tripJpa.isEmpty()) {
            logger.warn("Trip not found!");
            throw new IllegalArgumentException("Trip not found!");
        }
        return assembler.toDomain(tripJpa.get());
    }

    /**
     * Saves a new trip to the repository.
     *
     * This method takes a Trip object as input parameter and attempts to save it to the repository.
     * Since the save method always saves an object, in order to verify its uniqueness we must first
     * check if a trip with the same tripId already exists in the repository, using
     * the `existsById` method of the JpaRepository. If a trip with the same tripId exists, it throws
     * a TripIdAlreadyExistsException.
     *
     * If the trip is new (i.e., does not exist in the repository), it maps the Trip object to a TripJPA
     * object using the assembler's `toData` method, representing the data entity of the trip. The TripJPA
     * object is then saved to the repository using the `save` method of the JpaRepository.
     *
     * @param trip The Trip object representing the trip to be saved.
     * @return The same Trip object after it is successfully saved to the repository.
     * @throws TripIdAlreadyExistsException If a trip with the same tripId already exists in the repository.
     */
    @Override
    public Trip save(Trip trip) {
        if (jpaRepository.existsById(trip.getTripId())) {
            throw new TripIdAlreadyExistsException() {
            };
        }
        TripJPA toSave = assembler.toData(trip);
        jpaRepository.save(toSave);
        return trip;
    }

    /**
     * Updates an existing trip in the repository with the provided changes.
     *
     * This method takes a Trip object as input parameter, representing the updated state of the trip,
     * and attempts to update the corresponding trip in the repository. It maps the Trip object to a
     * TripJPA object using the assembler's `toData` method, representing the data entity of the trip.
     * The TripJPA object is then saved to the repository using the `save` method of the JpaRepository,
     * effectively updating the trip's data.
     *
     * @param trip The Trip object representing the updated state of the trip to be patched.
     * @return The same Trip object after it is successfully updated in the repository.
     */
    @Override
    public Trip patchTrip(Trip trip) {
        TripJPA toSave = assembler.toData(trip);
        jpaRepository.save(toSave);
        return trip;
    }

    /**
     * Deletes a trip from the repository based on the provided tripId.
     *
     * This method takes a TripId as input parameter and attempts to delete the corresponding trip
     * from the repository. It first checks if a trip with the specified tripId exists in the repository
     * using the `existsById` method of the JpaRepository. If the trip exists, it is deleted from the
     * repository using the `removeByTripId` method of the JpaRepository, and the method returns true,
     * indicating that the deletion was successful. If the trip does not exist, the method returns false.
     *
     * @param tripId The unique identifier of the trip to be deleted.
     * @return True if the trip is successfully deleted, false if the trip with the specified tripId does not exist.
     */
    @Override
    public boolean deleteByTripId(TripId tripId) {
        boolean isDeleted = false;
        if (jpaRepository.existsById(tripId)) {
            jpaRepository.removeByTripId(tripId);
            isDeleted = true;
        }
        return isDeleted;
    }

    /**
     * Retrieves a list of all available trips from the repository.
     *
     * This method retrieves all trips from the repository using the `findAll` method of the JpaRepository.
     * If no trips are found, it throws an IllegalArgumentException with the message "No Trips saved."
     * Otherwise, it maps the list of TripJPA objects to a list of Trip objects using the assembler's `listToDomain` method.
     *
     * @return A list of Trip objects, each representing an available trip in the repository.
     * @throws IllegalArgumentException If no trips are found in the repository.
     */
    @Override
    public List<Trip> findAll() {
        List<TripJPA> listFound = jpaRepository.findAll();
        if(listFound.isEmpty()){
            throw new IllegalArgumentException("No Trips saved.");
        }

        return assembler.listToDomain(listFound);
    }
}
