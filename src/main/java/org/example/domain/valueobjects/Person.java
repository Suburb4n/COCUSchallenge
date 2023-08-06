package org.example.domain.valueobjects;

import lombok.Getter;
import org.example.domain.interfaces.ValueObject;
import java.util.Objects;

public class Person implements ValueObject {
    @Getter
    private Name name;
    @Getter
    private TripId tripId;

    public Person(Name name, TripId tripId) {
        this.name = name;
        this.tripId = tripId;
    }

    public Person() {
    }

    public boolean checkSame(Person person) {
        return this.equals(person);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person people = (Person) o;
        return Objects.equals(name, people.name) && Objects.equals(tripId, people.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tripId);
    }


}
