package homeWork6.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import homeWork3.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование проекта accuweather.com")
@Feature(value = "Тестирование API Location API")
public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест GetLocationTest - поиск объекта Location")
    @Description("Данный тест предназначен для поиска Location по ключу Krasnodar")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Story(value = "Получение объекта Location для Krasnodar")
    @Owner("Ризванова Лия")
    void getLocation_search_returnKrasnodar() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Krasnodar")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(2,response.size());
        Assertions.assertEquals("Krasnodar", response.get(0).getEnglishName());
    }
}
