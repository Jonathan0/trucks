package com.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetTrucks {

    public static JSONArray getTrucks(int dayorder) throws IOException {
        URL url = new URL(String.format("https://data.sfgov.org/resource/jjew-r69b.json?dayorder=%d", dayorder));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int status = connection.getResponseCode();
        JSONArray truck_array = null;
        System.out.println(status);
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
                e.printStackTrace();
            }
        }

        return truck_array;
    }
}
