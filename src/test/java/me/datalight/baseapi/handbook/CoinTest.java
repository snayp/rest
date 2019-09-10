package me.datalight.baseapi.handbook;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import me.datalight.baseapi.BaseTest;
import me.datalight.baseapi.api.CoinBig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled
public class CoinTest extends BaseTest {

    private final static String path = "/handbook/coin/?limit=10000";

    @Test
    void testGetCoinStatus() {
        when().
            get(path).
        then().
            statusCode(200);
    }

    @Test
    void testGetCoinCount() {
        Response response = get(baseUri+basePath+path);
        log.info(baseUri+basePath+path);
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
        try {
            assertThat(count, greaterThan(2000));
        } catch (AssertionError e) {
            session.sendMessage(channel, "В ответе https://datalight.me/v1/handbook/coin меньше 2000 коинов! count = " + count);
        }
    }

    @Test
    void testGetCoinAttributesNotNull(){
        Response response = get(path);
        log.info(path);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Coin> coins = jsonPathEvaluator.getList("results", Coin.class);

        coins.forEach(coin -> {
            try {
                assertThat(coin.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://datalight.me/admin/handbook/coin");
            }
            try {
                assertThat(coin.getSymbol(),is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле symbol https://datalight.me/admin/handbook/coin/" + coin.getId());
            }
            try {
                assertThat(coin.getName(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле name в ответе https://datalight.me/admin/handbook/coin/" + coin.getId());
            }
            try {
                assertThat(coin.getLogo(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле logo в ответе https://datalight.me/admin/handbook/coin/" + coin.getId());
            }
            try {
                assertThat(coin.getRank(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле rank в ответе https://datalight.me/admin/handbook/coin/" + coin.getId());
            }
        });
    }

    private String searchPath = "https://datalight.me/v1/handbook/coin/";

    @Test
    void testGetSearch() {
        Response response = get(searchPath+"bitcoin");
        log.info(searchPath+"bitcoin");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
        try {
            assertThat(count, equalTo(41));
        } catch (AssertionError e) {
            session.sendMessage(channel, "В ответе https://base-api.datalight.me/v1/handbook/coin/index/?q=bitcoin не 41 коин! count = " + count);
        }
        List<CoinBig> coinsBig = jsonPathEvaluator.getList("result", CoinBig.class);
        coinsBig.forEach(coin -> {
            try {
                assertThat(coin.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://base-api.datalight.me/v1/handbook/coin/index/?q=bitcoin");
            }
            try {
                assertThat(coin.getSymbol(),is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле symbol https://base-api.datalight.me/v1/handbook/coin/index/?q=" + coin.getId());
            }
            try {
                assertThat(coin.getName(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле name в ответе https://base-api.datalight.me/v1/handbook/coin/index/?q=" + coin.getId());
            }
            try {
                assertThat(coin.getLogo(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле logo в ответе https://base-api.datalight.me/v1/handbook/coin/index/?q=" + coin.getId());
            }
            try {
                assertThat(coin.getRank(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле rank в ответе https://base-api.datalight.me/v1/handbook/coin/index/?q=" + coin.getId());
            }
            log.info(coin.getId());
        });
    }
}
