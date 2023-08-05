package org.example.domain.Trip;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TravelDuration;
import org.example.domain.valueobjects.TripId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripFactory implements TripFactoryInt {


    @Override
    public Trip createTrip(TripId tripId, City origCity, City destCity, TravelDuration date) {
        return new Trip(tripId, origCity, destCity,date);
    }
    @Override
    public Trip createTrip(TripId tripId, City origCity, City destCity, TravelDuration date, List<People> people) {
        return new Trip(tripId, origCity, destCity,date, people);
    }
}
