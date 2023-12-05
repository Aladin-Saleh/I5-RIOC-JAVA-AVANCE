package fr.unilasalle.flight.api.ressources;


import java.util.List;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.repositories.FlightsRepositories;
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

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
public class FlightsRessource 
{


    @Inject
    FlightsRepositories flightsRepositorie;

    @GET
    public List<Flight> getAllFlights()
    {
        return flightsRepositorie.getAllFlights();
    }

    @GET
    @Path("/destination/{destination}")
    public Response getFlightByDestination(@PathParam("destination") String destination)
    {
        List<Flight> flight = flightsRepositorie.getFlightsByDestination(destination);
        if (flight == null || flight.isEmpty())
        {
            String message = "Aucun vol ne correspond à cette destination";
            return Response.status(404).entity(message).build();
        }

        if (destination.equals("") || destination == null || destination.isBlank())
        {
            String message = "La destination ne peut pas être vide";
            return Response.status(404).entity(message).build();
        }

        return flight.isEmpty() ? Response.status(404).build() : Response.status(200).entity(flight).build();
    }

    @GET
    @Path("/number/{number}")
    public Response getFlightByNumber(@PathParam("number") String number)
    {
        Flight flight = flightsRepositorie.getFlightByNumber(number);
        if (flight == null || flight.getNumber() == null || flight.getNumber().isEmpty())
        {
            String message = "Aucun vol ne correspond à ce numéro";
            return Response.status(404).entity(message).build();
        }

        if (number == null || number.equals("") || number.isBlank())
        {
            String message = "Le numéro ne peut pas être vide";
            return Response.status(404).entity(message).build();
        }

        return flight == null ? Response.status(404).build() : Response.status(200).entity(flight).build();
    }

    @POST
    @Transactional
    public Response addFlight(Flight flight)
    {
        // Check the flight object is not null
        Flight flightCheck = flightsRepositorie.getFlightByNumber(flight.getNumber());
        if (flightCheck != null)
        {
            return Response.status(409).entity(flightCheck).build();
        }

        flightsRepositorie.addFlight(flight);
        return Response.status(201).entity(flight).build();
    }


    @DELETE
    @Path("/{number}")
    @Transactional
    public Response deleteFlight(@PathParam("number") String number)
    {
        Flight flight = flightsRepositorie.getFlightByNumber(number);
        if (number == null || number.equals("") || number.isBlank())
        {
            String message = "Le numéro ne peut pas être vide";
            return Response.status(404).entity(message).build();
        }

        if (flight == null)
        {
            String message = "Aucun vol ne correspond à ce numéro";
            return Response.status(404).entity(message).build();
        }

        flightsRepositorie.deleteFlight(flight);
        return Response.status(200).entity(flight).build();
    }


    
}
