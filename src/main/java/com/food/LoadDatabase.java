package com.food;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static com.utils.GetTrucks.getTrucks;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Value("${dayOrder}")  //this is default value set in appplication.properties
    private int dayOrder;

    private static final String [] DAY_OF_WEEK = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Bean
    CommandLineRunner initDatabase(TruckRepository repository) {
        JSONArray trucksArray = null;

        try {
            trucksArray = getTrucks(dayOrder);
            for (Object obj : trucksArray) {
                JSONObject truck = (JSONObject) obj;
                String permitLocation = truck.get("location").toString();
                double latitude = Double.parseDouble(truck.get("latitude").toString());
                double longitude = Double.parseDouble(truck.get("longitude").toString());
                String starttime = truck.get("starttime").toString();
                String endtime = truck.get("endtime").toString();
                log.info("Preloading " + repository.save(new Truck(permitLocation, latitude, longitude, starttime, endtime)));
            }
        } catch (IOException e) {
            log.error("Can not find trucks", e);
        }

        JSONArray finalTrucksArray = trucksArray;
        return args -> {
            log.info("Preloading " + finalTrucksArray.size() + " trucks completed!");
            log.info("Day of Week: " + DAY_OF_WEEK[dayOrder] + " !");
            // log.info("Preloading " + repository.save(new Truck("505 HOWARD ST", 37.788387888445406, -122.396123930376618)));
        };
    }
}