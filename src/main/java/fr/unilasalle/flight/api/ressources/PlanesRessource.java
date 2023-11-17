package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.repositories.PlanesRepositorie;

import java.util.List;

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

@Path("/planes")
@Produces(MediaType.APPLICATION_JSON) // Permet de dire que la ressource produit du JSON
@Consumes(MediaType.APPLICATION_JSON) // Permit de dire que la ressource consomme du JSON
public class PlanesRessource
{
    
    @Inject
    PlanesRepositorie planesRepositorie;

    @GET
    public List<Plane> getAllPlanes()
    {
        return planesRepositorie.getAllPlanes();
    }

    @GET
    @Path("/registration/{registration}")
    public Response getPlaneByRegistration(@PathParam("registration") String registration)
    {
        Plane plane = planesRepositorie.getPlaneByRegistration(registration);
        return plane == null ? Response.status(404).build() : Response.ok(plane).build();
    }

    @GET
    @Path("/operator/{operator}")
    public Response getPlanesByOperator(@PathParam("operator")String operator)
    {
        List<Plane> planes = planesRepositorie.getPlanesByOperator(operator);
        return planes.isEmpty() ? Response.status(404).build() : Response.ok(planes).build();
    }  

    @GET
    @Path("/id/{id}")
    public Response getPlaneById(@PathParam("id") int id)
    {
        Plane plane = planesRepositorie.getPlaneById(id);
        return plane == null ? Response.status(404).build() : Response.ok(plane).build();
    }

    @POST
    @Path("/add")
    @Transactional
    public Response addPlane(Plane plane)
    {
        planesRepositorie.addPlane(plane);
        return Response.ok(plane).status(201).build();
    }



}
