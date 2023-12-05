package fr.unilasalle.flight.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import fr.unilasalle.flight.api.beans.Flight;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;
import java.time.LocalDateTime;

@QuarkusTest
public class FlightsRessourceTests {

    @Test
    public void testGetAllFlights() {
        given()
                .when().get("/flights")
                .then()
                .statusCode(200)
                .body(is("[]")); // Si la réponse attendue est une liste vide
    }

    @Test
    public void testGetFlightByDestinationNotFound() {
        given()
                .when().get("/flights/destination/Paris")
                .then()
                .statusCode(404); // Si aucun vol pour Paris n'est trouvé
    }

    @Test
    public void testGetFlightByNumberNotFound() {
        given()
                .when().get("/flights/number/123")
                .then()
                .statusCode(404); // Si le numéro de vol 123 n'existe pas
    }


    @Test
    public void testDeleteFlightNotFound() {
        given()
                .when().delete("/flights/123")
                .then()
                .statusCode(404); // Si le vol avec le numéro 123 n'existe pas
    }

}
