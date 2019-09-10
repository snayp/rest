package me.datalight.baseapi.dashboard;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import me.datalight.baseapi.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DefaultDashboardTest extends BaseTest {
    protected final static String path = "/dashboard/dashboard";
    private String keyDefaultPath = "/?key=DEFAULT";
    List<Dashboard> dashboards;

    Response response;
    JsonPath jsonPathEvaluator;

    @BeforeEach
    void setup() {
        response = get(path+keyDefaultPath);
        log.info(baseUri+basePath+path+keyDefaultPath);
        jsonPathEvaluator = response.jsonPath();
        dashboards = jsonPathEvaluator.getList("", Dashboard.class);
    }

    @Test
    void testGetDefaultDasgboard() {
        dashboards.forEach(d-> {
                    try {
                        assertThat(d.getId(), equalTo(414));
                    } catch (AssertionError e) {
                        session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyDefaultPath + " id дашборда не 414! id = " + d.getId());
                    }
                    try {
                        assertThat(d.getTitle(), equalTo("DataLight"));
                    } catch (AssertionError e) {
                        session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyDefaultPath + " title дашборда не DataLight! title = " + d.getTitle());
                    }
                    try {
                        assertThat(d.getKey(), equalTo("DEFAULT"));
                    } catch (AssertionError e) {
                        session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyDefaultPath + " key дашборда не DEFAULT! key = " + d.getKey());
                    }
                    try {
                        assertThat(d.getMoving(), equalTo("E"));
                    } catch (AssertionError e) {
                        session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyDefaultPath + " moving дашборда не E! moving = " + d.getMoving());
                    }
                }
        );
    }
}
