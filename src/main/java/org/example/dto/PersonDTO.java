package org.example.dto;

import org.springframework.hateoas.RepresentationModel;

public class PersonDTO extends RepresentationModel<PersonDTO> {

    public Long tripId;
    public String firstName;
    public String lastName;
}
