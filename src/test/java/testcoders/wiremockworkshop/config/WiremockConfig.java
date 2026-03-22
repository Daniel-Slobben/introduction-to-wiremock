package testcoders.wiremockworkshop.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import testcoders.wiremockworkshop.javamappings.JavaMappings;

public class WiremockConfig {
  private final WireMockServer wireMockServer = new WireMockServer(0);

  public WiremockConfig() {
    wireMockServer.start();
    new JavaMappings(wireMockServer);
  }

  public WireMockServer getWireMockServer() {
    return wireMockServer;
  }
}
