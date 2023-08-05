package org.example.domain.Trip;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.example.domain.interfaces.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trip implements AggregateRoot<TripId> {
    @Getter
    private final TripId tripId;
    @Getter
    private List<People> people;
    @Getter
    private final City orgCity;
    @Getter
    private final City destCity;
    @Getter
    private final TravelDuration date;

    protected Trip(TripId tripId,  City orgCity, City destCity, TravelDuration date) {
        this.tripId = tripId;
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.date = date;
        this.people = new ArrayList<>();
    }

    protected Trip(TripId tripId,  City orgCity, City destCity, TravelDuration date, List<People> people) {
        this.tripId = tripId;
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.date = date;
        this.people = new ArrayList<>(people);
    }

    public void addPeople(List<People> peopleToAdd){
        for(int i = 0; i<peopleToAdd.size();i++){
            if(this.people.contains(peopleToAdd.get(i))){
                continue;
            }
            people.add(peopleToAdd.get(i));
        }
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
