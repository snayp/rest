package me.datalight.baseapi.api;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class MainTest extends BaseApiTest {

    private final static String ping = "/ping";
    private long unixTime = System.currentTimeMillis() / 1000L;

    @Test
    void testGetStatus() {
        when().
            get(baseUri).
        then().
            statusCode(200);
    }

    @Test
    void testPing() {
        Response response = get(baseUri+ping);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Double bodyAsDouble = new Double(bodyAsString);
        log.info(baseUri+ping);
        log.info("На сервере = " + bodyAsString);
        log.info("Текущее время = " + unixTime);
        try {
            assertThat(bodyAsDouble, closeTo(unixTime, 10));
        } catch (AssertionError e) {
            session.sendMessage(channel, "В ответе " + baseUri+ping + " время отличается от текущего больше, чем на 10 секунд");
        }
    }
}
