package org.example.services;
import org.example.domain.valueobjects.TripId;
import org.example.repositories.TripRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteTripService {

    private TripRepositoryInt repository;

    @Autowired
    public DeleteTripService(TripRepositoryInt repository) {
        this.repository = repository;
    }

    /**
     * Deletes a trip identified by the provided tripId.
     *
     * This method marks the transactional boundary using the @Transactional annotation, allowing us to delete
     * records from the database.
     * It takes a TripId as input parameter and attempts to delete the corresponding trip from the repository.
     * If found returns a True result, if not Found returns a false result.
     * The repository's `deleteByTripId` method is used to perform the deletion.
     *
     * @param tripId The unique identifier of the trip to be deleted.
     * @return True if the trip is successfully deleted, false otherwise.
     * @Transactional The method is marked as transactional to ensure that the deletion operation is atomic and consistent.
     */
    @Transactional
    public boolean deleteTripById(TripId tripId){
        return repository.deleteByTripId(tripId);
    }
}
