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
@Table(name = "planes")
public class Plane extends PanacheEntityBase
{

    @Id
    @SequenceGenerator(name = "planes_sequence", sequenceName = "planes_sequence", initialValue = 1, allocationSize = 1)
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planes_sequence")
    private int id;
    
    @Setter
    @Getter
    @Column(nullable = false, name = "capacity")
    private int capacity;

    @Setter
    @Getter
    @Column(nullable = false, name = "operator")
    private String operator;

    @Setter
    @Getter
    @Column(nullable = false, name = "model")
    private String model;
    
    @Setter
    @Getter
    @Column(nullable = false, name = "registration", unique = true)
    private String registration;

    @Override
    public String toString()
    {
        return "Plane [capacity=" + capacity + ", id=" + id + ", model=" + model + ", operator=" + operator
                + ", registration=" + registration + "]";
    }




}
