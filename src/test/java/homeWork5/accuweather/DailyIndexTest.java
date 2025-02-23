package homeWork5.accuweather;

import homeWork5.accuweather.indices.Metadata;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyIndexTest extends AbstractTest {

    private static final Logger logger
            = LoggerFactory.getLogger(DailyIndexTest.class);

    @Test
    void getListDailyIndices_shouldReturn200() throws IOException {
        logger.info("Тест код ответ 200 запущен");
        ObjectMapper mapper = new ObjectMapper();
        Metadata indexData = new Metadata();
        indexData.setName("Air Conditioning Index");

        logger.debug("Формируем мок GET /indices/v1/daily");
        stubFor(get(urlPathEqualTo("/indices/v1/daily"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(mapper.writeValueAsString(indexData))));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        logger.debug("http-клиент создан");

        HttpGet request = new HttpGet(getBaseUrl() + "/indices/v1/daily");

        HttpResponse response = httpClient.execute(request);

        verify(getRequestedFor(urlPathEqualTo("/indices/v1/daily")));
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Air Conditioning Index", mapper.readValue(response
                .getEntity().getContent(), Metadata.class).getName());
    }


    @Test
    void getListDailyIndices_shouldReturn401() throws IOException, URISyntaxException {
        logger.info("Тест код ответ 401 запущен");
        //given
        logger.debug("Формируем мок GET /indices/v1/daily");
        stubFor(get(urlPathEqualTo("/indices/v1/daily"))
                .withQueryParam("apiKey", containing("29ZgA9DHbWblpjAfwFGeVCEXIVjcQ2BW"))
                .willReturn(aResponse()
                        .withStatus(401).withBody("Unauthorized")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl()+"/indices/v1/daily");
        URI uri = new URIBuilder(request.getURI())
                .addParameter("apiKey", "P_29ZgA9DHbWblpjAfwFGeVCEXIVjcQ2BW")
                .build();
        request.setURI(uri);
        logger.debug("http клиент создан");
        //when
        HttpResponse response = httpClient.execute(request);
        //then
        verify(getRequestedFor(urlPathEqualTo("/indices/v1/daily")));
        assertEquals(401, response.getStatusLine().getStatusCode());
        assertEquals("Unauthorized", convertResponseToString(response));
    }
}