package org.example.domain.Trip;

import lombok.Getter;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trip {
    @Getter
    private final TripId tripId;
    @Getter
    private final City orgCity;
    @Getter
    private final City destCity;
    @Getter
    private final Date date;

    protected Trip(TripId tripId, City orgCity, City destCity, Date date) {
        this.tripId = tripId;
        this.orgCity = orgCity;
        this.destCity = destCity;
        this.date = date;
    }

    public boolean checkSame(Trip trip){
        return this.equals(trip);
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
