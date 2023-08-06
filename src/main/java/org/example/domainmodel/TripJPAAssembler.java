package org.example.domainmodel;
import org.example.domain.Trip.Trip;
import org.example.domain.Trip.TripFactoryInt;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.Person;
import org.example.domain.valueobjects.TravelDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripJPAAssembler {

    private TripFactoryInt factory;

    private PersonJPAAssembler peopleAssembler;

    @Autowired
    public TripJPAAssembler(TripFactoryInt factory, PersonJPAAssembler peopleAssembler){
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
            List<PersonJPA> peopleJpa = peopleAssembler.listToData(trip.getPeople(), tripJpa);
            tripJpa.setPeople(peopleJpa);
        }
        return tripJpa;
    }

    public Trip toDomain(TripJPA tripJpa){
        City origCity = new City(tripJpa.getOrigCity());
        City destCity = new City(tripJpa.getDestCity());
        TravelDuration duration = new TravelDuration(tripJpa.getDeparture(),
                tripJpa.getDeparture());

        if(isPeopleJpaPopulated(tripJpa.getPeople())){
            List<Person> people = peopleAssembler.listToDomain(tripJpa.getPeople());
          return  factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration, people);
        }
        return factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration);
    }

    public List<Trip> listToDomain(List<TripJPA> triplList){
        List<Trip> tripList = new ArrayList<>();

        for(int i = 0; i<triplList.size(); i++){
            tripList.add(toDomain(triplList.get(i)));
        }
        return tripList;
    }

    private static boolean isPeoplePopulated(List<Person> people){
        return !people.isEmpty();
    }
    private static boolean isPeopleJpaPopulated(List<PersonJPA> people){
        return !people.isEmpty();
    }
}
