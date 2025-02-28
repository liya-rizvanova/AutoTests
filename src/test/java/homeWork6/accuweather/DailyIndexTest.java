package homeWork6.accuweather;

import homeWork3.accuweather.indices.DailyIndex;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование проекта accuweather.com")
@Feature(value = "Тестирование API Indices API")
public class DailyIndexTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест DailyIndexTest - получение ежедневных индексов")
    @Description("Этот тест проверяет получение списка ежедневных погодных индексов и и сравнивает название первого элемента с ожидаемым значением")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение ежедневных погодных индексов")
    @Owner("Ризванова Лия")
    void getDailyIndex() {

        List<DailyIndex> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", DailyIndex.class);

        Assertions.assertEquals(118,response.size());
        Assertions.assertEquals("Air Conditioning Index", response.get(0).getName());
    }
}
