package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@NoArgsConstructor
public class TripId {
    @Getter
    @Setter
    private Long tripId;

    public TripId(Long tripId){
        this.tripId = tripId;
    }

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


}
