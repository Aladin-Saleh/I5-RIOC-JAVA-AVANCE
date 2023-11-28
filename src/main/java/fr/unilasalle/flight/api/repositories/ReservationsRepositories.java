package fr.unilasalle.flight.api.repositories;

import java.util.List;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.beans.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ReservationsRepositories implements PanacheRepositoryBase<Reservation, Integer>
{
    
    public List<Reservation> getAllReservations()
    {
        return this.listAll();
    }

    public List<Reservation> getReservationsByFlightId(int flight_id)
    {
        return this.find("flight_id", flight_id).list();
    }

    public long getReservationsCountByFlightId(int flight_id)
    {
        return this.find("flight_id", flight_id).count();
    }

    public void addReservationForRegisteredPassenger(Reservation reservation)
    {
        Flight flight   = new FlightsRepositories().getFlightById(reservation.getFlight_id());
        Plane plane     = new PlanesRepositories().getPlaneById(flight.getPlane_id());

        if (this.getReservationsCountByFlightId(reservation.getFlight_id()) >= plane.getCapacity())
        {
            throw new RuntimeException("Plane is full");
        }
        
        this.persist(reservation);

    }

    public void addReservationForUnregisteredPassenger(Reservation reservation, Passenger passenger)
    {
        Flight flight   = new FlightsRepositories().getFlightById(reservation.getFlight_id());
        Plane plane     = flight != null ? new PlanesRepositories().getPlaneById(flight.getPlane_id()) : null;

        new PassengersRepositories().addPassenger(passenger);
        reservation.setPassenger_id(passenger.getId());

        if (flight == null)
        {
            throw new RuntimeException("Flight not found");
        }

        if (plane == null)
        {
            throw new RuntimeException("Plane not found");
        }

        if (this.getReservationsCountByFlightId(reservation.getFlight_id()) >= plane.getCapacity())
        {
            throw new RuntimeException("Plane is full");
        }

        this.persist(reservation);
    }

    public void deleteReservation(Reservation reservation)
    {
        this.delete(reservation);
    }

    public void deleteReservationByPassengerId(int passenger_id)
    {
        if (new PassengersRepositories().getPassengerById(passenger_id) == null)
        {
            throw new RuntimeException("Passenger not found");
        }
        this.delete("passenger_id", passenger_id);
    }

    public void deleteReservationByFlightId(int flight_id)
    {
        this.delete("flight_id", flight_id);
    }


}
