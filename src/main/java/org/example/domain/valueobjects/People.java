package org.example.domain.valueobjects;

import lombok.Getter;
import org.example.domain.interfaces.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

public class People implements ValueObject {
    @Getter
    private Name name;
    @Getter
    private TripId tripId;


    public People(Name name, TripId tripId) {
        this.name = name;
        this.tripId = tripId;
    }

    public People() {
    }

    public boolean checkSame(People people) {
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
