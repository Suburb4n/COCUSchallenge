package org.example.domainmodel;
import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactoryInt;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.People;
import org.example.domain.valueobjects.TravelDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripJPAAssembler {

    private TripFactoryInt factory;

    private PeopleJPAAssembler peopleAssembler;

    @Autowired
    public TripJPAAssembler(TripFactoryInt factory, PeopleJPAAssembler peopleAssembler){
        this.factory=factory;
        this.peopleAssembler = peopleAssembler;
    }
    public TripJPA toData(Trip trip){

        String origCity = trip.getOrgCity().getCity();
        String destCity = trip.getDestCity().getCity();
        LocalDate departure = trip.getDate().getDeparture();
        LocalDate arrival = trip.getDate().getArrival();

        TripJPA tripJpa = new TripJPA(trip.getTripId(), origCity, destCity,
                departure, arrival);

        if(isPeoplePopulated(trip.getPeople())){
            List<PeopleJPA> listJpa = peopleAssembler.listToData(trip.getPeople(), tripJpa);
            tripJpa.setPeople(listJpa);
        }
        return tripJpa;
    }

    public Trip toDomain(TripJPA tripJpa){
        City origCity = new City(tripJpa.getOrigCity());
        City destCity = new City(tripJpa.getDestCity());
        TravelDuration duration = new TravelDuration(tripJpa.getDeparture(),
                tripJpa.getDeparture());

        if(isPeopleJpaPopulated(tripJpa.getPeople())){
            List<People> list = peopleAssembler.listToDomain(tripJpa.getPeople());
          return  factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration, list);
        }
        return factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration);
    }

    public List<Trip> listToDomain(List<TripJPA> list){
        List<Trip> tripList = new ArrayList<>();

        for(int i = 0; i<list.size(); i++){
            tripList.add(toDomain(list.get(i)));
        }
        return tripList;
    }

    private static boolean isPeoplePopulated(List<People> people){
        return !people.isEmpty();
    }
    private static boolean isPeopleJpaPopulated(List<PeopleJPA> people){
        return !people.isEmpty();
    }
}
