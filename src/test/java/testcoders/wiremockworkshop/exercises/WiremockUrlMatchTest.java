package testcoders.wiremockworkshop.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import testcoders.wiremockworkshop.config.EnvironmentValues;
import testcoders.wiremockworkshop.config.WiremockConfig;

@ExtendWith(WiremockConfig.class)
class WiremockUrlMatchTest {

  private static Stream<UUID> provideUUIDs() {
    return Stream.generate(UUID::randomUUID).limit(10);
  }

  @Test
  void urlMatch() {
    Response response = RestAssured.given().baseUri(EnvironmentValues.BASE_URL).get("health-check");

    assertThat(response.getStatusCode()).isEqualTo(200);
  }

  @ParameterizedTest
  @MethodSource("provideUUIDs")
  void urlMatchWithWildcard(UUID uuid) {
    Response response =
        RestAssured.given().baseUri(EnvironmentValues.BASE_URL).get("movies/{id}", uuid);

    assertThat(response.getStatusCode()).isEqualTo(200);
  }

  @Test
  void specificUrlMatch() {
    String uuid = "6301b6d9-131e-4022-9f72-7eeb50f3cd7d";

    Response response =
        RestAssured.given().baseUri(EnvironmentValues.BASE_URL).get("movies/" + uuid);

    assertThat(response.getStatusCode()).isEqualTo(400);
  }
}
