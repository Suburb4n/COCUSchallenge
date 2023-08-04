package org.example.repositories;

import org.example.domain.Trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepository implements TripRepositoryInt {

    private List<Trip> trips;

    @Autowired
    public TripRepository(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public Boolean save(Trip trip) {
        if (!this.trips.contains(trip)) {
            trips.add(trip);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(Trip trip) {
        boolean isDeleted = false;
        for (int i = 0; i < trips.size(); i++) {
            if (trip.checkSame(trips.get(i))) {
                trips.remove(i);
                isDeleted=true;
            }
        }
        return isDeleted;
    }

    @Override
    public List<Trip> findAll() {
        try {
            List<Trip> tripsFound = new ArrayList<>();
            tripsFound.addAll(trips);

            return new ArrayList<>(trips);
        } catch (Exception e) {
            throw new NullPointerException("List is Empty");
        }
    }
}
