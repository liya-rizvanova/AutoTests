package homeWork5;

import com.github.tomakehurst.wiremock.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

/**
 * Демо для демонстрации запуска сервиса
 */
public class RunWireMock {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080); // указал порт напрямую
        wireMockServer.start();
        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/test/urlequal"))
                .willReturn(aResponse().withBody("Welcome to test!")));

        System.out.println("WireMock запущен на http://localhost:8080/test/urlequal");

        // бесконечный цикл для удержания сервера
        while (true) {}
    }
}
