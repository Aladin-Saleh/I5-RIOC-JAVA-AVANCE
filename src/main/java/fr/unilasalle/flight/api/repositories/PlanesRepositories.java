package fr.unilasalle.flight.api.repositories;

import java.util.List;

import fr.unilasalle.flight.api.beans.Plane;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PlanesRepositories implements PanacheRepositoryBase<Plane, Integer> 
{

    
    public List<Plane> getAllPlanes()
    {
        return this.listAll();
    }

    public List<Plane> getAllPlanesIds()
    {
        return this.list("id");
    }



    public Plane getPlaneByRegistration(String registration)
    {
        return this.find("registration", registration).firstResult();
    }

    public List<Plane> getPlanesByOperator(String operator)
    {
        return this.find("operator", operator).list();
    }

    public Plane getPlaneById(int id)
    {
        return this.findById(id);
    }

    public void addPlane(Plane plane)
    {
        this.persist(plane);
    }



}
