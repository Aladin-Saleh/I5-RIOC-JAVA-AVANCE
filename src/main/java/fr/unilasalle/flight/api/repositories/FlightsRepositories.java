package fr.unilasalle.flight.api.repositories;

import java.util.List;

import fr.unilasalle.flight.api.beans.Flight;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FlightsRepositories implements PanacheRepositoryBase<Flight, Integer>
{

    

    public List<Flight> getAllFlights()
    {
        return this.listAll();
    }

    public List<Flight> getFlightsByDestination(String destination)
    {
        return this.find("destination", destination).list();
    }

    public Flight getFlightById(int id)
    {
        return this.findById(id);
    }


    public Flight getFlightByNumber(String number)
    {
        return this.find("number", number).firstResult();
    }

    public void addFlight(Flight flight)
    {
        this.persist(flight);
    }

    public void deleteFlight(Flight flight)
    {
        new ReservationsRepositories().deleteReservationByFlightId(flight.getId());
        this.delete(flight);
    }




    
}
