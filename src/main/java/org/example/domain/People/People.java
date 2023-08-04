package org.example.domain.People;

import org.example.domain.Trip.Trip;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;

import java.util.Objects;

public class People {

    private final Name name;

    private final TripId tripId;


    protected People(Name name, TripId tripId) {
        this.name = name;
        this.tripId = tripId;
    }

    public boolean checkSame(People people){
        return this.equals(people);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People)) return false;
        People people = (People) o;
        return Objects.equals(name, people.name) && Objects.equals(tripId, people.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tripId);
    }


}
