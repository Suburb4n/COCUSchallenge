package org.example.domain.Trip;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;

import java.util.List;

public interface TripFactoryInt {

    Trip createTrip(TripId tripId, City origCity,
                    City destCity, TravelDuration date);

    Trip createTrip(TripId tripId, City origCity,
                    City destCity, TravelDuration date,
                    List<People> people);
}
