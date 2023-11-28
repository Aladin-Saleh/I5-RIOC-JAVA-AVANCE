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
@Table(name = "reservations")
@Setter
@Getter
public class Reservation extends PanacheEntityBase
{

    @Id
    @SequenceGenerator(name = "reservations_sequence", sequenceName = "reservations_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_sequence")
    private int id;


    @Column(nullable = false, name = "flight_id")
    private int flight_id;

    @Column(nullable = false, name = "passenger_id")
    private int passenger_id;

    @Override
    public String toString()
    {
        return "Reservation [flight_id=" + flight_id + ", id=" + id + ", passenger_id=" + passenger_id + "]";
    }

    
}
