package testcoders.wiremockworkshop.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import testcoders.wiremockworkshop.config.WiremockConfig;
import wiremock.org.eclipse.jetty.http.HttpStatus;

class UrlMatchTest {
  private final WireMockServer wireMockServer = new WiremockConfig().getWireMockServer();

  private static Stream<UUID> provideUUIDs() {
    return Stream.generate(UUID::randomUUID).limit(10);
  }

  @Test
  void urlMatch() {
    // execute
    Response response = RestAssured.given().baseUri(wireMockServer.baseUrl()).get("health-check");

    // verify
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK_200);
  }

  @Test
  void specificUrlMatch() {
    // prepare
    String uuid = "6301b6d9-131e-4022-9f72-7eeb50f3cd7d";

    // execute
    Response response = RestAssured.given().baseUri(wireMockServer.baseUrl()).get("actor/" + uuid);

    // verify
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST_400);
  }

  @ParameterizedTest
  @MethodSource("provideUUIDs")
  void urlMatchWithWildcard(UUID uuid) {
    // execute
    Response response =
        RestAssured.given().baseUri(wireMockServer.baseUrl()).get("actor/{id}", uuid);

    // verify
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK_200);
  }
}
