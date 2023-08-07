package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exceptions.DepartureAfterArrivalException;
import org.example.domain.interfaces.ValueObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.example.exceptions.NoNullDatesException;

@NoArgsConstructor
public class TravelDuration implements ValueObject {
    @Getter
    private List<IllegalArgumentException> exceptions = new ArrayList<>();
    @Getter
    private LocalDate arrival;
    @Getter
    private LocalDate departure;

    public TravelDuration(LocalDate departure, LocalDate arrival) {
        if(departure == null || arrival == null){
            exceptions.add(new NoNullDatesException()) ;
        }
        if(exceptions.isEmpty()) {
            this.departure = departure;
            this.arrival = arrival;
            validateDates(departure, arrival);
        }
    }

    private void validateDates(LocalDate departure, LocalDate arrival) {
        if(departure.isEqual(departure) && arrival.isBefore(departure)){
            exceptions.add(new DepartureAfterArrivalException());
        }
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
