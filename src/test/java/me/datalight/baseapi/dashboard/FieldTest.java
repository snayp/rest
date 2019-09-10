package me.datalight.baseapi.dashboard;

import com.google.gson.reflect.TypeToken;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import me.datalight.baseapi.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FieldTest extends BaseTest {

    Response response;
    ResponseBody body;
    Type collectionType;
    Collection<Field> fields;
    JsonPath jsonPathEvaluator;
    String path = "/dashboard";

    @BeforeEach
    void setup() {
        // Get response
        response = get(path + "/field");
        body = response.getBody();
        // Deserialization
        collectionType = new TypeToken<Collection<Field>>(){}.getType();
        fields = gson.fromJson(body.asString(), collectionType);
        // Extract json path
        jsonPathEvaluator = response.jsonPath();
    }

    @Test
    void testGetFieldStatus() {
        when().
            get(path + "/field").
        then().
            statusCode(200);
    }

    @Test
    void testGetFieldIdsArray() {

        List<String> fieldIds = jsonPathEvaluator.getList("id");
        log.info(String.valueOf(fieldIds));
        log.info(baseUri+basePath+path+"/field");
        assertThat(fieldIds, hasItems(
                "Coin",
                "coinmarketcap.coin.price_btc",
                "coinmarketcap.coin.price_usd",
                "coinmarketcap.coin.rank",
                "Date",
                "etherscan.main.new_holders",
                "global.Coin.price_7d_image",
                "rating.auto.rate",
                "reddit.coin.online",
                "reddit.coin.subscribers"));
        assertThat(fieldIds, hasSize(greaterThan(10)));
    }

    @Test
    void testGetFieldAttributesNotNull() {

        Collection<Field> visibleFields = new ArrayList<>();
        fields.forEach(field -> {
            if (field.isVisible()) {
                visibleFields.add(field);
            }
        });

        log.info("Count of visible fields = " + String.valueOf(visibleFields.size()));
        visibleFields.forEach(field -> {
            try {
                assertThat(field.getDescription(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле description https://datalight.me/admin/dashboard/field/" + field.getIdWith5F());
            }
        });

        fields.forEach(field -> {
            try {
                assertThat(field.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://datalight.me/admin/dashboard/field");
            }
            try {
                assertThat(field.getTable(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле title https://datalight.me/admin/dashboard/field/ " + field.getIdWith5F());
            }
            try {
                assertThat(field.getGroup(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значение в поле group https://datalight.me/admin/dashboard/field/" + field.getIdWith5F());
            }
            try {
                assertThat(field.getTable(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значение в поле table https://datalight.me/admin/dashboard/field/" + field.getIdWith5F());
            }
            try {
                assertThat(field.isVisible(), is(notNullValue()));
            } catch (AssertionError e) {
                session.sendMessage(channel, "null в поле visible https://datalight.me/admin/dashboard/field/" + field.getIdWith5F());
            }
        });
    }
}
