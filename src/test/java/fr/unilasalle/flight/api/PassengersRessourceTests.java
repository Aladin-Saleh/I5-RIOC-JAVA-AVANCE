package fr.unilasalle.flight.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import fr.unilasalle.flight.api.beans.Passenger;



@QuarkusTest
public class PassengersRessourceTests 
{
    
    @Test
    public void testGetAllPassengers() {
        given()
          .when().get("/passengers")
          .then()
          .statusCode(200)
          .body(is("[]")); // Si la réponse attendue est une liste vide
    }

    @Test
    public void testGetPassengerByIdNotFound() {
        given()
          .when().get("/passengers/1")
          .then()
          .statusCode(404); // Si le passager avec l'ID 1 n'est pas trouvé
    }





  
}
