package homeWork6.accuweather;

import homeWork3.accuweather.currentConditions.CurrentCondition;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование проекта accuweather.com")
@Feature(value = "Тестирование API Weather API")
public class CurrentConditionsTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест CurrentConditionsTest - получение текущих погодных условий")
    @Description("Этот тест запрашивает и проверяет текущие погодные условия для указанного Location ID (293686)")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение текущих погодных условий")
    @Owner("Ризванова Лия")
    void getCurrentConditions() {

        List<CurrentCondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/293686")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", CurrentCondition.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("http://www.accuweather.com/en/ru/krasnodar/293686/current-weather/293686?lang=en-us",
                response.get(0).getMobileLink());
    }
}
