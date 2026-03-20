package testcoders.wiremockworkshop.exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testcoders.wiremockworkshop.config.EnvironmentValues;
import testcoders.wiremockworkshop.config.WiremockConfig;

import static org.assertj.core.api.Assertions.assertThat;

class WiremockUrlMatchTest extends WiremockConfig {

    @Test
    void urlMatch() {
        Response response = RestAssured.given()
                .baseUri(EnvironmentValues.BASE_URL)
                .get("hello-world");

        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
