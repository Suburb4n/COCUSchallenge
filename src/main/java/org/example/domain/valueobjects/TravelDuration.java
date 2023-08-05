package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.interfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
public class TravelDuration implements ValueObject {
    @Getter
    private LocalDate arrival;
    @Getter
    private LocalDate departure;

    public TravelDuration(LocalDate startDate, LocalDate endDate) {
        this.arrival = startDate;
        this.departure = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelDuration)) return false;
        TravelDuration date = (TravelDuration) o;
        return Objects.equals(arrival, date.arrival) && Objects.equals(departure, date.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrival, departure);
    }
}
