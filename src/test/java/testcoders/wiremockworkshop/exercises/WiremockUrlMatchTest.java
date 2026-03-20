package testcoders.wiremockworkshop.exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import testcoders.wiremockworkshop.config.EnvironmentValues;
import testcoders.wiremockworkshop.config.WiremockConfig;
import testcoders.wiremockworkshop.dto.HealthcheckResponse;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(WiremockConfig.class)
class WiremockUrlMatchTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void urlMatch() throws JsonProcessingException {
        Response response = RestAssured.given()
                .baseUri(EnvironmentValues.BASE_URL)
                .get("health-check");

        assertThat(response.getStatusCode()).isEqualTo(200);

        HealthcheckResponse healthcheckResponse = objectMapper.readValue(response.getBody().asString(), HealthcheckResponse.class);
        assertThat(healthcheckResponse.status()).isEqualTo("Hello World!");
    }

    @ParameterizedTest
    @MethodSource("provideUUIDs")
    void urlMatchWithWildcard(UUID uuid) {
        Response response = RestAssured.given()
                .baseUri(EnvironmentValues.BASE_URL)
                .get("movies/{id}", uuid);

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    void specificUrlMatch() {
        String uuid = "6301b6d9-131e-4022-9f72-7eeb50f3cd7d";

        Response response = RestAssured.given()
                .baseUri(EnvironmentValues.BASE_URL)
                .get("movies/" + uuid);

        assertThat(response.getStatusCode()).isEqualTo(400);
    }

    private static Stream<UUID> provideUUIDs() {
        return Stream.generate(UUID::randomUUID).limit(10);
    }
}
