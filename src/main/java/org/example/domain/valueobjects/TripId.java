package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exceptions.NullTripIdException;
import org.example.domain.interfaces.DomainId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
public class TripId implements DomainId, Serializable {
    @Getter
    private List<IllegalArgumentException> exception = new ArrayList<>();
    @Getter
    private Long tripId;

    public TripId(Long tripId){
        if(tripId == null){
            exception.add( new NullTripIdException());
        }
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
