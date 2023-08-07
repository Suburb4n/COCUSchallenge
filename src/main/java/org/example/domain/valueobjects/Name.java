package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.interfaces.ValueObject;
import org.example.exceptions.InvalidFirstNameException;
import org.example.exceptions.InvalidLastNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Name implements ValueObject {
    @Getter
    private List<IllegalArgumentException> exceptions = new ArrayList<>();
    @Getter
    private String firstName;

    @Getter
    private String lastName;

    public Name(String firstName, String lastName) {
        validateFirstName(firstName);
        validateLastName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }
    private void validateFirstName(String firstName) {
        if (firstName == null || !validNameFormat(firstName) || firstName.isEmpty()) {
            exceptions.add(new InvalidFirstNameException());
        }
        // Add other validation checks for the first name
    }

    private void validateLastName(String lastName) {
        if (lastName == null || !validNameFormat(lastName) || lastName.isEmpty()) {
            exceptions.add(new InvalidLastNameException());
        }
        // Add other validation checks for the last name
    }

    public boolean validNameFormat(String name) {

        return name.matches("^[a-zA-Z\\s-']+");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
