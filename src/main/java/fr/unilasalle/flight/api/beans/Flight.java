package fr.unilasalle.flight.api.beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "flights")
@Getter
@Setter
public class Flight extends PanacheEntityBase 
{
    

    @Id
    @SequenceGenerator(name = "flights_sequence", sequenceName = "flights_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_sequence")
    private int id;

    @Column(nullable = false, name = "number", unique = true)
    private String number;

    @Column(nullable = false, name = "origin")
    private String origin;

    @Column(nullable = false, name = "destination")
    private String destination;

    @Column(nullable = false, name = "departure_date")
    private Timestamp departure_date;

    @Column(nullable = false, name = "departure_time")
    private Timestamp departure_time;

    @Column(nullable = false, name = "arrival_date")
    private Date arrival_date;

    @Column(nullable = false, name = "arrival_time")
    private Date arrival_time;

    @Column(nullable = false, name = "plane_id")
    private int plane_id;

    @Override
    public String toString()
    {
        return "Flight [arrival_date=" + arrival_date + ", arrival_time=" + arrival_time + ", departure_date="
                + departure_date + ", departure_time=" + departure_time + ", destination=" + destination + ", id=" + id
                + ", number=" + number + ", origin=" + origin + ", plane_id=" + plane_id + "]";
    }











}
