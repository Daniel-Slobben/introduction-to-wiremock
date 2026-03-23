package testcoders.wiremockworkshop.exercises;

import static org.assertj.core.api.Assertions.assertThat;
import static testcoders.wiremockworkshop.config.ObjectMapperConfig.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import testcoders.wiremockworkshop.config.WiremockConfig;
import testcoders.wiremockworkshop.dto.Actor;
import testcoders.wiremockworkshop.dto.Movie;

class ReturnBodyTest {
  private final WireMockServer wireMockServer = new WiremockConfig().getWireMockServer();

  @Test
  void returnBody() throws JsonProcessingException {
    // prepare
    var actors = Set.of(new Actor(UUID.fromString("d10e6eea-0598-4a76-b643-5d074f7aa7e3"), "Neo"));
    var expectedMovie =
        new Movie(
            UUID.fromString("cf39f10c-2701-4b1e-9a0d-638cbcaf64a4"),
            "The Matrix",
            LocalDate.of(2000, 12, 31),
            actors);

    // execute
    Response response =
        RestAssured.given()
            .baseUri(wireMockServer.baseUrl())
            .get("/movies/{id}", UUID.randomUUID());

    // verify
    Movie actualMovie = objectMapper.readValue(response.getBody().print(), Movie.class);
    assertThat(actualMovie).usingRecursiveComparison().isEqualTo(expectedMovie);
  }
}
