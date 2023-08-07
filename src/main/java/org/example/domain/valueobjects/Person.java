package org.example.domain.valueobjects;

import lombok.Getter;
import org.example.domain.interfaces.ValueObject;
import org.example.exceptions.InvalidPersonNameException;
import java.util.List;
import java.util.Objects;

public class Person implements ValueObject {
    @Getter
    private Name name;
    @Getter
    private TripId tripId;

    public Person(Name name, TripId tripId) {
        this.name = name;
        validateName(name);
        this.tripId = tripId;
    }

    private void validateName(Name name) throws IllegalArgumentException {
        List<IllegalArgumentException> exceptions = name.getExceptions();
        if(!name.getExceptions().isEmpty()){
            throw new InvalidPersonNameException(exceptions);
        }
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
