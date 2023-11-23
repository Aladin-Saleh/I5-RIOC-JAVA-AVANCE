package fr.unilasalle.flight.api.ressources;


import java.util.List;

import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.repositories.FlightsRepositories;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
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
        return flight.isEmpty() ? Response.status(404).build() : Response.status(200).entity(flight).build();
    }

    @GET
    @Path("/{number}")
    public Response getFlightByNumber(@PathParam("number") String number)
    {
        Flight flight = flightsRepositorie.getFlightByNumber(number);
        return flight == null ? Response.status(404).build() : Response.status(200).entity(flight).build();
    }

    @POST
    @Transactional
    public Response addFlight(Flight flight)
    {
        // Check the flight object is not null

        
        flightsRepositorie.addFlight(flight);
        return Response.status(201).entity(flight).build();
    }




    
}
