package me.datalight.baseapi.dashboard;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import me.datalight.baseapi.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class CoinDashboardTest extends BaseTest {

    private final static String path = "/dashboard/dashboard";
    private String keyIndexPath = "/?key=coin&q=BTC";
    private String keyDefaultPath = "/?key=coin&q=BTC";
    List<Dashboard> dashboards;

    Response response;
    JsonPath jsonPathEvaluator;

    @BeforeEach
    void setup() {
        response = get(path+keyIndexPath);
        log.info(baseUri+basePath+path+keyIndexPath);
        jsonPathEvaluator = response.jsonPath();
        dashboards = jsonPathEvaluator.getList("", Dashboard.class);
    }

    @Test
    void testGetCoinDashboard() {
        dashboards.forEach(d-> {
                try {
                    assertThat(d.getId(), equalTo(3377));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " id дашборда не 3377! id = " + d.getId());
                }
                try {
                    assertThat(d.getTitle(), equalTo("Coin dashboard"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " title дашборда не Coin dashboard! title = " + d.getTitle());
                }
                try {
                    assertThat(d.getKey(), equalTo("COIN"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " key дашборда не COIN! key = " + d.getKey());
                }
                try {
                    assertThat(d.getMoving(), equalTo("D"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " moving дашборда не D! moving = " + d.getMoving());
                }
            }
        );
    }


    @Test
    void testGetIndexWidgetsIds() {
        dashboards.forEach(dashboard -> {
            List<Integer> widgetIds = new ArrayList<>();
            List<Widget> indexWidgets = dashboard.getWidgets();
            for (Widget widget : indexWidgets) {
                widgetIds.add(widget.getId());
            }

            try {
                assertThat(widgetIds, hasSize(7));
            } catch (AssertionError e) {
                session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " не 7 виджетов! count = " + widgetIds.size());
            }

            try {
                assertThat(widgetIds, containsInAnyOrder(16224,16225,16219,16220,16221,16222,16223));
            } catch (AssertionError e) {
                session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " нет одного из виджетов с id = 16224,16225,16219,16220,16221,16222,16223");
            }
        });
    }
}
