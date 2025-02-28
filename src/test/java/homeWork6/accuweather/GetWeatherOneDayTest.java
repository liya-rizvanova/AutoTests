package homeWork6.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import homeWork3.accuweather.weather.Weather;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование проекта accuweather.com")
@Feature(value = "Тестирование API Weather API")
public class GetWeatherOneDayTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест GetWeatherOneDayTest - поиск прогноза погоды за 1 день")
    @Description("Данный тест предназначен для получения данных о погоде для Location ID (293686) за 1 день")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Вызов метода для получения прогноза погоды за 1 день")
    @Owner("Ризванова Лия")
    void getWeatherOneDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/293686")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(1,response.getDailyForecasts().size());
    }
}
