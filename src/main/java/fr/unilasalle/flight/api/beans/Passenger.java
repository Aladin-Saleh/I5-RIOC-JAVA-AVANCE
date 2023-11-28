package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "passengers")
@Setter
@Getter
public class Passenger extends PanacheEntityBase
{

    @Id
    @SequenceGenerator(name = "passengers_sequence", sequenceName = "passengers_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passengers_sequence")
    private int id;

    @Column(nullable = false, name = "firstname")
    private String firstname;

    @Column(nullable = false, name = "surname")
    private String surname;

    @Column(nullable = false, name = "email_address", unique = true)
    private String email_address;

    @Override
    public String toString()
    {
        return "Passenger [email_address=" + email_address + ", firstname=" + firstname + ", id=" + id + ", surname="
                + surname + "]";
    }
    
}
