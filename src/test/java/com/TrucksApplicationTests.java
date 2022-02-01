package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.utils.GetTrucks.*;

@SpringBootTest
class TrucksApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getTrucksTest() throws IOException {
        JSONArray res = getTrucks(0);
        Assertions.assertTrue(res.size() > 0);
        Assertions.assertNotNull(((JSONObject) res.get(0)).get("optionaltext"));
    }

    @Test
    public void getDistanceTest() {
        double latitude1 = 37.788387888445406;
        double longitude1 = -122.39612393037662;
        double latitude2 = 37.79223595577547;
        double longitude2 = -122.40037044651142;

        double dis = calculateDistance(latitude1, longitude1, latitude2, longitude2);
        Assertions.assertEquals(567.7404856237969, dis);
    }

    @Test
    public void getDayOfOrderTest() {
        String inputDay = System.getenv("dayOrder");
        Assertions.assertTrue(0 <= Integer.parseInt(inputDay) && 6 >= Integer.parseInt(inputDay));
    }
}
