package me.datalight.baseapi.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class ObjectTest extends BaseApiTest {

    private String searchPath = "/object?q=";
    private String search = "hype";
    private String path = "/object";

    @Test
    void testGetIndex() {
        Response response = get(baseUri+basePath+path);
        log.info(baseUri+basePath+path);
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
//        try {
//            assertThat(count, equalTo(78));
//        } catch (AssertionError e) {
//            session.sendMessage(channel, "В ответе " + baseUri + basePath + path + " не 78 филдов! count = " + count);
//        }
    }

    @Test
    void testGetSearch() {
        Response response = get(baseUri+basePath+searchPath+search);
        log.info(baseUri+basePath+searchPath+search);
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.getInt("count");
//        try {
//            assertThat(count, equalTo(28));
//        } catch (AssertionError e) {
//            session.sendMessage(channel, "В ответе https://api.datalight.me/v1/object/?q=" + search + " не 28 филдов! count = " + count);
//        }
        List<Object> objects = jsonPathEvaluator.getList("result", Object.class);
        objects.forEach(object -> {
            try {
                assertThat(object.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://api.datalight.me/v1/object/?q=" + search);
            }
//            try {
//                assertThat(object.getGroup(),is(not(equalTo(""))));
//            } catch (AssertionError e) {
//                session.sendMessage(channel, "Пустое значениe в поле group https://api.datalight.me/v1/object/?q=" + search);
//            }
//            try {
//                assertThat(object.getTitle(), is(not(equalTo(""))));
//            } catch (AssertionError e) {
//                session.sendMessage(channel, "Пустое значениe в поле title в ответе https://api.datalight.me/v1/object/?q=" + search);
//            }
            try {
                assertThat(object.getField_type(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле field_type в ответе https://api.datalight.me/v1/object/?q=" + search);
            }
            try {
                assertThat(object.getData_type(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле data_type в ответе https://api.datalight.me/v1/object/?q=" + search);
            }
            log.info(object.getTitle());
        });
    }
}
