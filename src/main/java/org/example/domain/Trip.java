package org.example.domain;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;

import java.util.List;
import java.util.Objects;

public class Trip {
    private final TripId tripId;
    private List<Name> names;

    private final City orgCity;

    private final City destCity;

    private final Date date;

    protected Trip(TripId tripId, List<Name> names, City orgCity, City destCity, Date date) {
        this.tripId = tripId;
        this.names = names;
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.date = date;
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
}
