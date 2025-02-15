package homeWork3.accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import homeWork3.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    void getLocation_search_returnKrasnodar() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Krasnodar")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(2,response.size());
        Assertions.assertEquals("Krasnodar", response.get(0).getEnglishName());
    }
}
