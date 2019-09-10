package me.datalight.baseapi.utils;

import me.datalight.baseapi.api.BaseApiTest;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static io.restassured.RestAssured.when;

/**
 * Created by dshilin on 08.02.19.
 */
public class Load extends BaseApiTest {

//    @Test
    void load() {
        IntStream.range(0, 150000).forEach(x -> {
            when().
                    get("https://bugdone.datalight.me/icons-e775be722aba9e080cebac103d5929e2/favicon.ico").
            then().
                    statusCode(404);
        });
    }
}
