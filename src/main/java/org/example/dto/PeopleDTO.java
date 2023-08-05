package org.example.dto;

import org.springframework.hateoas.RepresentationModel;

public class PeopleDTO extends RepresentationModel<PeopleDTO> {

    public Long tripId;
    public String firstName;
    public String lastName;
}
