package homeWork5.accuweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeWork5.accuweather.weather.DailyForecast;
import homeWork5.accuweather.weather.Headline;
import homeWork5.accuweather.weather.Weather;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetWeatherOneDayTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(GetWeatherOneDayTest.class);

    @Test
    void get_shouldReturn200() throws IOException, URISyntaxException {
        logger.info("Тест код ответ 200 запущен");

        // given
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        Headline headline = new Headline();
        headline.setCategory("Категория");
        headline.setText("Текст");
        weather.setHeadline(headline);
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.add(dailyForecast);
        weather.setDailyForecasts(dailyForecasts);

        logger.debug("Формирование мока для GET /forecasts/v1/daily/1day/293686");
        stubFor(get(urlPathEqualTo("/forecasts/v1/daily/1day/293686"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(mapper.writeValueAsString(weather))));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/1day/293686");
        logger.debug("HTTP клиент создан");

        // when
        HttpResponse response = httpClient.execute(request);

        // then
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/1day/293686")));
        assertEquals(200, response.getStatusLine().getStatusCode());

        Weather responseBody = mapper.readValue(response.getEntity().getContent(), Weather.class);
        assertEquals("Категория", responseBody.getHeadline().getCategory());
        assertEquals("Текст", responseBody.getHeadline().getText());
        assertEquals(1, responseBody.getDailyForecasts().size());
    }

    @Test
    void get_shouldReturn500() throws IOException {
        logger.info("Тест код ответ 500 запущен");

        // given
        logger.debug("Формирование мока для GET /forecasts/v1/daily/2day/293686");
        stubFor(get(urlPathEqualTo("/forecasts/v1/daily/2day/293686"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("ERROR")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/2day/293686");
        logger.debug("HTTP клиент создан");

        // when
        HttpResponse response = httpClient.execute(request);

        // then
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/2day/293686")));
        assertEquals(500, response.getStatusLine().getStatusCode());
        assertEquals("ERROR", convertResponseToString(response));
    }
}
