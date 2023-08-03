package org.example.domain;

import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;

public class Traveller {

    private final TripId tripId;

    private final Name name;

    protected Traveller(TripId tripId, Name name) {
        this.tripId = tripId;
        this.name = name;
    }
}
