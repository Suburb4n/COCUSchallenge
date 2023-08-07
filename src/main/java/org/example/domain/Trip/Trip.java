package org.example.domain.Trip;

import lombok.Getter;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.domain.interfaces.AggregateRoot;
import org.example.exceptions.InvalidTripParamsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trip implements AggregateRoot<TripId> {
    @Getter
    private List<IllegalArgumentException> exceptions = new ArrayList<>();
    @Getter
    private final TripId tripId;
    @Getter
    private List<Person> people;
    @Getter
    private final City orgCity;
    @Getter
    private final City destCity;
    @Getter
    private final TravelDuration travelDuration;


    /**
     * Creates a new Trip object with the provided tripId, origin city, destination city, and travel duration.
     *
     * This constructor initializes a new Trip object with the given TripId, origin City, destination City, and
     * TravelDuration. It also validates the tripId and travelDuration to ensure they meet the required criteria.
     * If the tripId or travelDuration is invalid, an exception will be thrown.
     *
     * Outside of the domain and domainTest package it can only be called by its respective Factory (TripFactory).
     *
     * After initializing the Trip object, an empty ArrayList is created for the people participating in the trip.
     * The Trip object is then checked for any invalid parameters.
     * If any exceptions occurred during validation,
     * an InvalidTripParamsException is thrown with the corresponding exception messages.
     *
     * @param tripId The TripId representing the unique identifier of the trip.
     * @param orgCity The City representing the origin city of the trip.
     * @param destCity The City representing the destination city of the trip.
     * @param travelDuration The TravelDuration representing the duration of the trip.
     * @throws InvalidTripParamsException If there are any invalid parameters in the tripId or travelDuration.
     */
    protected Trip(TripId tripId, City orgCity, City destCity, TravelDuration travelDuration) {
        this.tripId = tripId;
        validateTripId(tripId);
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.travelDuration = travelDuration;
        validateTravelDuration();
        this.people = new ArrayList<>();

        if(!exceptions.isEmpty()){
            throw new InvalidTripParamsException(exceptions);
        }

    }

    private void validateTripId(TripId tripId) {
        if (!tripId.getException().isEmpty()) {
            exceptions.add(tripId.getException().get(0));
        }
    }

    private void validateTravelDuration() {
        if (!travelDuration.getExceptions().isEmpty()) {
            exceptions.add(travelDuration.getExceptions().get(0));
        }
    }

    protected Trip(TripId tripId, City orgCity, City destCity, TravelDuration date, List<Person> people) {
        this.tripId = tripId;
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.travelDuration = date;
        this.people = new ArrayList<>(people);
    }

    public void addPeople(Person personToAdd) {
        if (this.people.contains(personToAdd)) {
            throw new IllegalArgumentException("Person is already on trip");
        }
        people.add(personToAdd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return Objects.equals(tripId, trip.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId);
    }

    @Override
    public TripId identity() {
        return tripId;
    }

    @Override
    public boolean sameAs(Object object) {
        if (object instanceof Trip) {
            Trip trip = (Trip) object;

            if (this.tripId.equals(trip.identity())) {
                return true;
            }
        }
        return false;
    }


}
