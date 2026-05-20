package testcoders.wiremockworkshop.exercises;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testcoders.wiremockworkshop.config.WiremockConfig;
import testcoders.wiremockworkshop.dto.Actor;
import testcoders.wiremockworkshop.dto.Movie;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static testcoders.wiremockworkshop.config.ObjectMapperConfig.objectMapper;

class ReturnRequestTest {
  private final WireMockServer wireMockServer = new WiremockConfig().getWireMockServer();

  @Test
  void createMovieReturnsGeneratedId() throws JsonProcessingException {
    // prepare
    var actors = Set.of(new Actor(UUID.fromString("d10e6eea-0598-4a76-b643-5d074f7aa7e3"), "Neo"));
    var movieToCreate = new Movie(
            null,
            "The Matrix",
            LocalDate.of(2000, 12, 31),
            actors);

    // execute
    Response response = RestAssured.given()
            .baseUri(wireMockServer.baseUrl())
            .contentType("application/json")
            .body(objectMapper.writeValueAsString(movieToCreate))
            .post("/movies");

    // verify
    assertThat(response.statusCode()).isEqualTo(201);
    UUID returnedId = UUID.fromString(response.jsonPath().getString("movieID"));
    assertThat(returnedId).isNotNull();
  }
}