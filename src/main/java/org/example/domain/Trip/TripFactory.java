package org.example.domain.Trip;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.Name;
import org.example.domain.valueobjects.TripId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripFactory implements TripFactoryInt {


    @Override
    public Trip createTrip(TripId tripId, City origCity, City destCity, Date date) {
        return new Trip(tripId, origCity, destCity,date);
    }
}
