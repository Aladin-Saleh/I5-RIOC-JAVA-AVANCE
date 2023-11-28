package fr.unilasalle.flight.api.ressources;

import java.util.List;

import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.repositories.PassengersRepositories;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassengersRessource 
{
    
    @Inject
    PassengersRepositories passengersRepositorie;
    
    @GET
    public List<Passenger> getAllPassengers()
    {
        return passengersRepositorie.getAllPassengers();
    }

    @GET
    @Path("/{id}")
    public Response getPassengerById(@PathParam("id")int id)
    {
        try
        {
            return Response.ok(passengersRepositorie.getPassengerById(id)).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updatePassenger(@PathParam("id")int id, Passenger passenger)
    {
        try
        {
            passengersRepositorie.updatePassenger(id,passenger);
            return Response.status(Response.Status.OK).entity(passenger).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Transactional
    public Response addPassenger(Passenger passenger)
    {
        try
        {
            passengersRepositorie.addPassenger(passenger);
            return Response.status(Response.Status.CREATED).entity(passenger).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    

    









}
