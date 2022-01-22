package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.utils.GetTrucks.getTrucks;

@SpringBootTest
class TrucksApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getTrucksTest() throws IOException {
        JSONArray res = getTrucks(0);
        Assertions.assertEquals(82, res.size());
        Assertions.assertEquals("0", ((JSONObject) res.get(0)).get("dayorder"));
    }
}
