package org.example.domainmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.interfaces.ValueObject;

import javax.persistence.*;
@NoArgsConstructor
@Table(name = "People")
@Entity
public class PersonJPA implements ValueObject {
    @Id
    @Getter
    @Column(name="First_Name")
    private String firstName;
    @Getter
    @Column(name="Last_Name")
    private String lastName;

    @Getter
    private Long tripId;
    @Getter
    @ManyToOne
    @JoinColumn(name = "trip", nullable = false)
    private TripJPA trip;


    protected PersonJPA(String firstName, String lastName, Long tripId, TripJPA trip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tripId = tripId;
        this.trip = trip;
    }
}
