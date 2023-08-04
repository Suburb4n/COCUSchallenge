package org.example.domain.People;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;

public class People {

    private final Name name;

    private final TripId tripId;

    protected People(Name name, TripId tripId) {
        this.name = name;
        this.tripId = tripId;
    }
}
