package org.example.domain.valueobjects;

import lombok.Getter;
import org.example.domain.interfaces.ValueObject;
import org.example.exceptions.InvalidPersonNameException;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Objects;

public class Person implements ValueObject {
    @Getter
    private Name name;
    @Getter
    private TripId tripId;

    /**
     * Creates a new Person object with the provided name and tripId.
     *
     * This constructor initializes a new Person object with the given Name and TripId. It also validates
     * the name to ensure it meets the required criteria. If the name is invalid, an exception will be thrown.
     *
     * @param name The Name object representing the first name and last name of the person.
     * @param tripId The TripId representing the unique identifier of the trip to which the person belongs.
     * @throws InvalidNameException If the name is invalid or does not meet the required criteria.
     */
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
