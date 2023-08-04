package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
public class Date {
    @Getter
    private LocalDate arrival;
    @Getter
    private LocalDate departure;

    public Date(LocalDate startDate, LocalDate endDate) {
        this.arrival = startDate;
        this.departure = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return Objects.equals(arrival, date.arrival) && Objects.equals(departure, date.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrival, departure);
    }
}
