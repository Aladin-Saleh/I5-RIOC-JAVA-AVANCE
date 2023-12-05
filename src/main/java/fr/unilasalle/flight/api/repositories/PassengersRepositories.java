package fr.unilasalle.flight.api.repositories;

import java.util.List;

import fr.unilasalle.flight.api.beans.Passenger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PassengersRepositories implements PanacheRepositoryBase<Passenger, Integer> 
{

    public List<Passenger> getAllPassengers()
    {
        return this.listAll();
    }

    public Passenger getPassengerById(int id)
    {
        if (this.findById(id) == null)
        {
            throw new RuntimeException("Passenger not found");
        }
        return this.findById(id);
    }

    public Passenger getPassengerByEmail(String email)
    {
        return this.find("email_address", email).firstResult();
    }

    public void addPassenger(Passenger passenger)
    {
        if (this.getPassengerByEmail(passenger.getEmail_address()) != null)
        {
            throw new RuntimeException("Passenger already exists");
        }

        if (passenger.getEmail_address() == null || passenger.getFirstname() == null || passenger.getSurname() == null)
        {
            throw new RuntimeException("Missing information");
        }
        this.persist(passenger);
    }

    public void deletePassenger(Passenger passenger)
    {
        if (this.getPassengerById(passenger.getId()) == null)
        {
            throw new RuntimeException("Passenger not found");
        }
        this.delete(passenger);
    }

    public void updatePassenger(int id,Passenger passenger)
    {
        Passenger passengerToUpdate;
        if (this.getPassengerById(id) == null)
        {
            throw new RuntimeException("Passenger not found");
        }
        else
        {
            passengerToUpdate = this.getPassengerById(id);
        }

        if (passenger.getEmail_address() != null)
        {
            passengerToUpdate.setEmail_address(passenger.getEmail_address());
        }

        if (passenger.getFirstname() != null)
        {
            passengerToUpdate.setFirstname(passenger.getFirstname());
        }

        if (passenger.getSurname() != null)
        {
            passengerToUpdate.setSurname(passenger.getSurname());
        }

        this.persist(passengerToUpdate);
    }









    
}
