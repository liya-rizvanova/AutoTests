package homeWork3.accuweather;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AccuweatherAbstractTest {

    private static final Properties prop = new Properties();
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream configFile = new FileInputStream("src/test/java/homeWork3/resources/accuweather.properties")) {
            prop.load(configFile);
            apiKey = System.getProperty("apikey", prop.getProperty("apikey"));
            baseUrl = System.getProperty("base_url", prop.getProperty("base_url"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + e.getMessage(), e);
        }
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
