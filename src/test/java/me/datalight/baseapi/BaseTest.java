package me.datalight.baseapi;

import com.google.gson.Gson;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected static SlackSession session;
    protected static SlackChannel channel;
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected static Gson gson = new Gson();
    protected final static String basePath = "/v1";
    protected final static String baseUri = "https://datalight.me";

    @BeforeAll
    public static void setupAll() throws Exception {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        session = Slack.connect();
        channel = session.findChannelByName("test");
        RestAssured.basePath = basePath;
        RestAssured.baseURI = baseUri;
    }
}
