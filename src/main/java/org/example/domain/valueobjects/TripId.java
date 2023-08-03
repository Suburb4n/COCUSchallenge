package org.example.domain.valueobjects;

import java.util.Objects;

public class TripId {

    private final Long tripId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripId)) return false;
        TripId tripId1 = (TripId) o;
        return Objects.equals(tripId, tripId1.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId);
    }

    public TripId(Long tripId){
        this.tripId = tripId;
    }
}
