package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Passenger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PassengersRepositories implements PanacheRepositoryBase<Passenger, Integer> 
{


    public Passenger getPassengerById(int id)
    {
        return this.findById(id);
    }

    public Passenger getPassengerByEmail(String email)
    {
        return this.find("email", email).firstResult();
    }

    public void addPassenger(Passenger passenger)
    {
        this.persist(passenger);
    }

    public void deletePassenger(Passenger passenger)
    {
        this.delete(passenger);
    }

    public void updatePassenger(Passenger passenger)
    {
        this.update("email", passenger.getEmail_address());
        this.update("firstname", passenger.getFirstname());
        this.update("surname", passenger.getSurname());

    }









    
}
