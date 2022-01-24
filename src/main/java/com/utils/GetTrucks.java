package com.utils;

import com.food.TruckNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetTrucks {

    private static final Logger log = LoggerFactory.getLogger(GetTrucks.class);

    public static JSONArray getTrucks(int dayorder) throws IOException {
        if(dayorder < 0 || dayorder > 6) {
            throw new TruckNotFoundException(-1L);
        }

        URL url = new URL(String.format("https://data.sfgov.org/resource/jjew-r69b.json?dayorder=%d", dayorder));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int status = connection.getResponseCode();
        JSONArray truck_array = null;

        if (status != 200) {
            throw new RuntimeException("HttpResponseCode: " + status);
        } else {
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            try {
                truck_array = (JSONArray) parse.parse(inline.toString());
            } catch (ParseException e) {
                log.error("Parse Exception: ", e);
            }
        }

        return truck_array;
    }

    /**
     * Calculate the distance between you and the truck
     * @param latitude1   current latitude
     * @param longitude1  current longitude
     * @param latitude2   truck latitude
     * @param longitude2  truck longitude
     * @return the distance to the truck
     */
    public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        final int R = 6371;  // Radius of the earth

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c * 1000; // convert to meters
    }
}
