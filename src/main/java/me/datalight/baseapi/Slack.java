package me.datalight.baseapi;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import java.io.IOException;

public class Slack {
    public static SlackSession connect() throws IOException {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession("");//ключ к слаку
        session.connect();
        return session;
    }
}
