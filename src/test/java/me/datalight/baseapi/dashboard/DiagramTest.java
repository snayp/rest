package me.datalight.baseapi.dashboard;

import com.google.gson.reflect.TypeToken;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import me.datalight.baseapi.BaseTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DiagramTest extends BaseTest {

    private String path = "dashboard/diagram";

    @Test
    void testGetDiagramStatus() {
        when().
            get(path).
        then().
            statusCode(200);
    }

    @Test
    void testGetDiagramAttributesNotNull(){
        Response response = get(path);
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();

        List<Integer> ids = jsonPathEvaluator.getList("id");
        ids.forEach(id -> assertThat(id, is(notNullValue())));

        List<Boolean> disables = jsonPathEvaluator.getList("disable");
        disables.forEach(v -> assertThat(v, is(notNullValue())));

        // Deserialization
        Type collectionType = new TypeToken<Collection<Diagram>>(){}.getType();
        Collection<Diagram> diagrams = gson.fromJson(body.asString(), collectionType);
        Collection<Diagram> enabledDiagrams = new ArrayList<>();
        diagrams.forEach(diagram -> {
            if (!diagram.isDisable()) {
                enabledDiagrams.add(diagram);
            }
        });

        log.info("Count of enabled diagrams = " + enabledDiagrams.size());

        enabledDiagrams.forEach(ed -> {
            try {
                assertThat(ed.getId(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Есть пустые значения в поле id в ответе https://datalight.me/v1/dashboard/diagram");
            }
            try {
                assertThat(ed.getTitle(),is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле title https://datalight.me/admin/dashboard/diagram/" + ed.getId());
            }
            try {
                assertThat(ed.getPicture(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле picture https://datalight.me/admin/dashboard/diagram/" + ed.getId());
            }
            try {
                assertThat(ed.getIcon(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле icon https://datalight.me/admin/dashboard/diagram/" + ed.getId());
            }
            try {
                assertThat(ed.getDescription(), is(not(equalTo(""))));
            } catch (AssertionError e) {
                session.sendMessage(channel, "Пустое значениe в поле description https://datalight.me/admin/dashboard/diagram/" + ed.getId());
            }
        });
    }
}
