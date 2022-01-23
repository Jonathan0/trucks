package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.utils.GetTrucks.getDistance;
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

    @Test
    public void getDistanceTest() {
        double latitude1 = 37.788387888445406;
        double longitude1 = -122.39612393037662;
        double latitude2 = 37.79223595577547;
        double longitude2 = -122.40037044651142;

        double dis = getDistance(latitude1, longitude1, latitude2, longitude2);
        Assertions.assertEquals(567.7404856237969, dis);
    }
}
