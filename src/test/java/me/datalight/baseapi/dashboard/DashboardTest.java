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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class DashboardTest extends BaseTest {

    protected final static String path = "/dashboard/dashboard";
    private String keyIndexPath = "/?key=INDEX";
    private String keyDefaultPath = "/?key=DEFAULT";
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
    void testGetIndexDashboard() {
        dashboards.forEach(d-> {
                try {
                    assertThat(d.getId(), equalTo(3376));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " id дашборда не 3376! id = " + d.getId());
                }
                try {
                    assertThat(d.getTitle(), equalTo("Index"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " title дашборда не Index! title = " + d.getTitle());
                }
                try {
                    assertThat(d.getKey(), equalTo("INDEX"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " key дашборда не INDEX! key = " + d.getKey());
                }
                try {
                    assertThat(d.getMoving(), equalTo("E"));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " moving дашборда не E! moving = " + d.getMoving());
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
                assertThat(widgetIds, hasSize(3));
            } catch (AssertionError e) {
                session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " не три виджета! count = " + widgetIds.size());
            }

            try {
                assertThat(widgetIds, containsInAnyOrder(16216,16217,16218));
            } catch (AssertionError e) {
                session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " нет одного из виджетов с id = 16216,16217,16218");
            }
        });
    }

    @Test
    void testGetIndexWidgets() {
        dashboards.forEach(dashboard -> {
            List<Widget> indexWidgets = dashboard.getWidgets();
            indexWidgets.forEach(iw->{
                try {
                    assertThat(iw.getTitle(), is(not(equalTo(""))));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " у виджета не заполнен title! widget id = " + iw.getId());
                }

                try {
                    assertThat(iw.getControl(), is(not(equalTo(""))));
                } catch (AssertionError e) {
                    session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " у виджета не заполнен control! widget id = " + iw.getId());
                }
            });
        });
    }

    @Test
    void testGetIndexCheckWidgetsMeta() {
        dashboards.forEach(dashboard -> {
            List<Widget> indexWidgets = dashboard.getWidgets();
            List<Widget> widgetsWithMeta = new ArrayList<>();
            indexWidgets.forEach(iw->{
                if (iw.getLayers_meta() != null){
                    widgetsWithMeta.add(iw);
                }
            });
            widgetsWithMeta.forEach(widget -> {
                log.info(String.valueOf(widget.getId()));
                List<Layer> layers = widget.getLayers();
//                List<String> outer_keys = new ArrayList<>();
                layers.forEach(layer -> {
//                    outer_keys.add(layer.getOuter_key());
                    log.info(layer.getOuter_key());
                    try {
                        assertThat(widget.getLayers_meta(), containsString(layer.getOuter_key()));
                    } catch (AssertionError e) {
                        session.sendMessage(channel, "В ответе " + baseUri+basePath+path+keyIndexPath + " у виджета widget id = " + widget.getId() + " есть layer с outer_key = " + layer.getOuter_key() + ", которого нет в layers_meta виджета.");
                    }
                });
            });
        });
    }

}
