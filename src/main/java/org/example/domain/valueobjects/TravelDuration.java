package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exceptions.DepartureAfterArrivalException;
import org.example.domain.interfaces.ValueObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.example.exceptions.NullDatesException;

@NoArgsConstructor
public class TravelDuration implements ValueObject {
    @Getter
    private List<IllegalArgumentException> exceptions = new ArrayList<>();
    @Getter
    private LocalDate arrival;
    @Getter
    private LocalDate departure;

    /**
     * Creates a new TravelDuration object with the provided departure and arrival dates.
     *
     * This constructor initializes a new TravelDuration object with the given departure and arrival dates.
     * It checks if the departure or arrival dates are null, and if so, it adds a NoNullDatesException to
     * the exceptions list. If both dates are non-null and there are no exceptions, it sets the departure
     * and arrival dates in the TravelDuration object and then validates the dates to ensure they meet the
     * required criteria. If the dates are invalid, an exception will be thrown.
     *
     * @param departure The LocalDate representing the departure date of the travel.
     * @param arrival The LocalDate representing the arrival date of the travel.
     * @throws NullDatesException If the departure or arrival dates are null.
     * @throws DepartureAfterArrivalException If the departure or arrival dates are invalid.
     */
    public TravelDuration(LocalDate departure, LocalDate arrival) {
        if(departure == null || arrival == null){
            exceptions.add(new NullDatesException()) ;
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
