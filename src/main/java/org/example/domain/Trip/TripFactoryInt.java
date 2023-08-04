package org.example.domain.Trip;

import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Date;
import org.example.domain.valueobjects.TripId;

import java.util.List;

public interface TripFactoryInt {

    Trip createTrip(TripId tripId, City origCity,
                    City destCity, Date date);
}
