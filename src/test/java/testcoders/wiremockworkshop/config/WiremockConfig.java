package testcoders.wiremockworkshop.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockConfig {
    private final WireMockServer wireMockServer;
    private final WireMock wireMock;

    public WiremockConfig() {
       wireMockServer = new WireMockServer(EnvironmentValues.PORT);
       wireMockServer.start();

       wireMock = new WireMock("localhost", EnvironmentValues.PORT);
    }

    public WireMock getWireMock() {
        return wireMock;
    }

    public WireMockServer getWireMockServer() {
        return wireMockServer;
    }
}
