package testcoders.wiremockworkshop.config;

import static testcoders.wiremockworkshop.config.EnvironmentValues.PORT;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import testcoders.wiremockworkshop.javamappings.JavaMappings;

public class WiremockConfig implements BeforeEachCallback, AfterEachCallback {

  private final WireMockServer wireMockServer;

  public WiremockConfig() {
    wireMockServer = new WireMockServer(PORT);
    WireMock.configureFor(PORT);
    new JavaMappings(wireMockServer);
  }

  @Override
  public void beforeEach(@NonNull ExtensionContext context) {
    wireMockServer.start();
  }

  @Override
  public void afterEach(@NonNull ExtensionContext context) {
    wireMockServer.stop();
  }
}
