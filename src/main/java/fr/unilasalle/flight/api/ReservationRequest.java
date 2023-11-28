package fr.unilasalle.flight.api;

import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Reservation;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservationRequest 
{

    private Reservation reservation;
    private Passenger passenger;
    private int flight_id;
    private int passenger_id;
    


    
}
