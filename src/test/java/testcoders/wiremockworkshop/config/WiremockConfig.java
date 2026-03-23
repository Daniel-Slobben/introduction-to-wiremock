package testcoders.wiremockworkshop.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.Response;
import testcoders.wiremockworkshop.javamappings.JavaMappings;

public class WiremockConfig {
  private final WireMockServer wireMockServer = new WireMockServer(0);

  public WiremockConfig() {
    wireMockServer.start();
    new JavaMappings(wireMockServer);
    wireMockServer.addMockServiceRequestListener(new Logger());
  }

  public WireMockServer getWireMockServer() {
    return wireMockServer;
  }
}

class Logger implements RequestListener {
  @Override
  public void requestReceived(Request request, Response response) {
    System.out.printf(
        """
              Wiremock received request on url: %s, method: %s, body:%s
            """,
        request.getUrl(), request.getMethod(), request.getBodyAsString());
    System.out.printf(
        """
              Wiremock returned request statuscode: %s, body:%s
            %n""",
        response.getStatus(), response.getBodyAsString());
  }
}
