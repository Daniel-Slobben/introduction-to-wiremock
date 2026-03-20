package testcoders.wiremockworkshop.javamappings;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class JavaMappings {

  private final WireMockServer wireMockServer;

  public JavaMappings(WireMockServer wireMockServer) {
    this.wireMockServer = wireMockServer;
    healthCheck();
  }

  public void healthCheck() {
    wireMockServer.stubFor(
        get(urlEqualTo("/health-check")).willReturn(WireMock.aResponse().withStatus(200)));
  }
}
