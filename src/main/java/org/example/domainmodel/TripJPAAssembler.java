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

    /**
     * Converts a domain entity (Trip) to a data entity (TripJPA).
     *
     * This method takes a Trip domain entity as input and converts it to a corresponding data entity,
     * TripJPA. It extracts the origin city, destination city, departure, and arrival properties from
     * the Trip domain entity's City and TravelDuration objects, respectively. It then constructs a new
     * TripJPA object using the extracted values and the provided TripId, representing the data entity
     * of the trip.
     *
     * If the Trip domain entity's people list is populated, it uses the PeopleAssembler to convert the
     * list of Person domain entities to a list of PersonJPA data entities. It sets the people list of
     * the newly created TripJPA with the converted data entities.
     *
     * @param trip The Trip domain entity to be converted to a data entity (TripJPA).
     * @return The TripJPA object representing the data entity of the trip.
     */
    public TripJPA toData(Trip trip){

        String origCity = trip.getOrgCity().getCity();
        String destCity = trip.getDestCity().getCity();
        LocalDate departure = trip.getTravelDuration().getDeparture();
        LocalDate arrival = trip.getTravelDuration().getArrival();

        TripJPA tripJpa = new TripJPA(trip.getTripId(), origCity, destCity,
                departure, arrival);

        if(isPeoplePopulated(trip.getPeople())){
            List<PersonJPA> peopleJpa = peopleAssembler.listToData(trip.getPeople(), tripJpa);
            tripJpa.setPeople(peopleJpa);
        }
        return tripJpa;
    }

    /**
     * Converts a data entity (TripJPA) to a domain entity (Trip).
     *
     * This method takes a TripJPA object as input and converts it to a corresponding domain entity,
     * Trip. It creates City objects for the origin and destination cities using the tripJpa's
     * origCity and destCity properties. It also creates a TravelDuration object using the tripJpa's
     * departure and arrival properties.
     *
     * If the tripJpa's people list is populated, it uses the PeopleAssembler to convert the list
     * of PersonJPA data entities to a list of Person domain entities. It then creates a Trip object
     * with the provided TripId, origin City, destination City, TravelDuration, and the list of People,
     * using the factory's createTrip method. If the people list is not populated, it creates a Trip
     * object without the list of People.
     *
     * @param tripJpa The data entity (TripJPA) to be converted to a domain entity (Trip).
     * @return The Trip object representing the domain model of the trip.
     */
    public Trip toDomain(TripJPA tripJpa){
        City origCity = new City(tripJpa.getOrigCity());
        City destCity = new City(tripJpa.getDestCity());
        TravelDuration duration = new TravelDuration(tripJpa.getDeparture(),
                tripJpa.getArrival());

        if(isPeopleJpaPopulated(tripJpa.getPeople())){
            List<Person> people = peopleAssembler.listToDomain(tripJpa.getPeople());
          return  factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration, people);
        }
        return factory.createTrip(tripJpa.getTripId(), origCity, destCity, duration);
    }

    /**
     * Converts a list of data entities (TripJPA) to a list of domain entities (Trip).
     *
     * This method takes a list of TripJPA data entities as input and converts each of them to the corresponding
     * domain entity, Trip. It iterates over the list of TripJPA objects and, for each TripJPA, invokes the `toDomain`
     * method to create the corresponding Trip domain entity. The resulting Trip objects are added to a new list,
     * which is then returned as the final result.
     *
     * @param trips The list of TripJPA data entities to be converted to domain entities (Trip).
     * @return A list of Trip objects.
     */
    public List<Trip> listToDomain(List<TripJPA> trips){
        List<Trip> tripList = new ArrayList<>();

        for(int i = 0; i<trips.size(); i++){
            tripList.add(toDomain(trips.get(i)));
        }
        return tripList;
    }

    protected static boolean isPeoplePopulated(List<Person> people){
        return !people.isEmpty();
    }
    protected static boolean isPeopleJpaPopulated(List<PersonJPA> people){
        return !people.isEmpty();
    }
}
