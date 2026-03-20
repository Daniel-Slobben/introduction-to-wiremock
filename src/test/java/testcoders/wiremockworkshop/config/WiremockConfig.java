package testcoders.wiremockworkshop.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static testcoders.wiremockworkshop.config.EnvironmentValues.PORT;

public class WiremockConfig implements BeforeEachCallback, AfterEachCallback {

    private WireMockServer wireMockServer;

    @Override
    public void beforeEach(@NonNull ExtensionContext context) {
        wireMockServer = new WireMockServer(PORT); // random port
        wireMockServer.start();
    }

    @Override
    public void afterEach(@NonNull ExtensionContext context) {
        wireMockServer.stop();
    }
}
