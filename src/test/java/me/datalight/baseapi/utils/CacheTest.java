package me.datalight.baseapi.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import me.datalight.baseapi.api.BaseApiTest;
import me.datalight.baseapi.api.CoinBig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static java.lang.Thread.sleep;

@Disabled
public class CacheTest extends BaseApiTest {
    private String path = "/coin";
    private String limit = "/?limit=20";

    @Test
    void testCache() {
        Response response = get(baseUri + basePath + path + limit);
        log.info(baseUri+basePath+path + limit);
        JsonPath jsonPathEvaluator = response.jsonPath();
        log.info(String.valueOf(System.currentTimeMillis() / 1000L));

        List<CoinBig> coinsBig = jsonPathEvaluator.getList("result", CoinBig.class);
        coinsBig.forEach(coin ->
        {
            when().
                get("https://datalight.me/coins/" + coin.getId()).
            then().
                statusCode(200);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        when().
            get("https://datalight.me/market").
        then().
            statusCode(200);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        when().
            get("https://datalight.me/coins").
        then().
            statusCode(200);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        when().
            get("https://datalight.me").
        then().
            statusCode(200);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.valueOf(System.currentTimeMillis() / 1000L));
    }
}
