package org.example.domainmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.domain.valueobjects.TripId;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Table(name="Trips")
@Entity
public class TripJPA {
    @Id
    @Getter
    private TripId tripId;
    @Getter
    @Column(name="Origin_City")
    private String origCity;
    @Getter
    @Column(name="Destination_City")
    private String destCity;
    @Getter
    private LocalDate departure;
    @Getter
    private LocalDate arrival;
    @Getter
    @Setter
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PeopleJPA> people;

    protected TripJPA(TripId tripId, String origCity, String destCity, LocalDate departure,
                      LocalDate arrival) {
        this.tripId = tripId;
        this.origCity = origCity;
        this.destCity = destCity;
        this.departure = departure;
        this.arrival = arrival;
        this.people = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripJPA)) return false;
        TripJPA tripJPA = (TripJPA) o;
        return Objects.equals(tripId, tripJPA.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId);
    }
}
