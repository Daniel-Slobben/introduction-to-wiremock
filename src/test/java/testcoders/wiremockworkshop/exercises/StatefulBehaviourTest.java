package testcoders.wiremockworkshop.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import testcoders.wiremockworkshop.config.WiremockConfig;
import wiremock.org.eclipse.jetty.http.HttpStatus;

class StatefulBehaviourTest {
  private final WireMockServer wireMockServer = new WiremockConfig().getWireMockServer();

  private Response getMovie() {
    return RestAssured.given()
        .baseUri(wireMockServer.baseUrl())
        .get("movies/{id}", UUID.randomUUID());
  }

  @Test
  void failThenSucceed() {
    Response response = getMovie();
    Response response2 = getMovie();
    Response response3 = getMovie();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK_200);
    assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR_500);
    assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK_200);
  }
}
