package fr.unilasalle.flight.api.ressources;

import java.util.List;

import fr.unilasalle.flight.api.ReservationRequest;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.PassengersRepositories;
import fr.unilasalle.flight.api.repositories.ReservationsRepositories;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;





@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationsRessource 
{
    
    @Inject
    ReservationsRepositories reservationsRepositorie;

    @GET
    public List<Reservation> getAllReservations()
    {
        return reservationsRepositorie.getAllReservations();
    }

    @GET
    @Path("/flight/{flightId}")
    public List<Reservation> getReservationsByFlightId(@PathParam("flightId") int flightId)
    {
        return reservationsRepositorie.getReservationsByFlightId(flightId);
    }

    @POST
    @Transactional
    public Response addReservation(ReservationRequest request)
    {

        Passenger passenger;
        if (request.getPassenger_id() == 0)
        {
           passenger = new PassengersRepositories().getPassengerByEmail(request.getPassenger().getEmail_address()) != null ? passenger = new PassengersRepositories().getPassengerByEmail(request.getPassenger().getEmail_address()) : request.getPassenger();
        }
        else
        {
            passenger = new PassengersRepositories().getPassengerById(request.getPassenger_id());
            if (passenger == null)
            {
                return Response.status(400).entity("Le passager n'existe pas").build();
            }
        }

        Reservation reservation = new Reservation();
        reservation.setPassenger_id(passenger.getId());
        reservation.setFlight_id(request.getFlight_id());

        try
        {
            if (reservation.getPassenger_id() > 0)
            {
                reservationsRepositorie.addReservationForRegisteredPassenger(reservation);
            }   
            else
            {
                reservationsRepositorie.addReservationForUnregisteredPassenger(reservation, passenger);
            }
        }
        catch (Exception e)
        {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.status(201).entity(reservation).build();
    }


    @DELETE
    @Transactional
    @Path("/{passenger_id}")
    public Response deleteReservationByPassengerId(@PathParam("passenger_id")int passengerId)
    {

        try
        {
            reservationsRepositorie.deleteReservationByPassengerId(passengerId);
        }
        catch (Exception e)
        {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.status(204).build();

    }

}
