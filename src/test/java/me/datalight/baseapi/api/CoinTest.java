package me.datalight.baseapi.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class CoinTest extends BaseApiTest {

    private String path = "/coin";
    private String search = "?q=btc";

    @Test
    void testGetCoinIndex() {
        Response response = get(baseUri+basePath+path);
        log.info(baseUri+basePath+ path);
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
        log.info(String.valueOf(count));
        try {
            assertThat(count, greaterThan(2070));
        } catch (AssertionError e) {
            session.sendMessage(channel, "В ответе https://api.datalight.me/v1/coin меньше 2070 коинов! count = " + count);
        }
    }

    @Test
    void testGetSearch() {
        Response response = get(baseUri+basePath+ path +search);
        log.info(baseUri+basePath+ path +search);
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
        log.info(String.valueOf(count));
        try {
            assertThat(count, equalTo(27));
        } catch (AssertionError e) {
            session.sendMessage(channel, "В ответе https://api.datalight.me/v1/coin/?q=btc не 27 коинов! count = " + count);
        }
        List<CoinBig> coinsBig = jsonPathEvaluator.getList("result", CoinBig.class);
        coinsBig.forEach(coin -> {
            try {
                assertThat(coin.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://api.datalight.me/v1/coin/?q=" + search);
            }
            try {
                assertThat(coin.getSymbol(),is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле symbol https://api.datalight.me/v1/coin/?q=" + search);
            }
            try {
                assertThat(coin.getName(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле name в ответе https://api.datalight.me/v1/coin/?q=" + search);
            }
            try {
                assertThat(coin.getLogo(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле logo в ответе https://api.datalight.me/v1/coin/?q=" + search);
            }
            try {
                assertThat(coin.getRank(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле rank в ответе https://api.datalight.me/v1/coin/?q=" + search);
            }
            log.info(coin.getId());
        });
    }
}
