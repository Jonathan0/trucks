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

    @Value("${dayOrder}")  //this is default value set in application.properties
    private int dayOrder;

    private static final String [] DAY_OF_WEEK = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Bean
    CommandLineRunner initDatabase(TruckRepository repository) throws NullPointerException {
        JSONArray trucksArray = null;

        try {
            trucksArray = getTrucks(dayOrder);
            for (Object o : trucksArray) {
                JSONObject truck = (JSONObject) o;
                String permitLocation = truck.get("location").toString();
                double latitude = Double.parseDouble(truck.get("latitude").toString());
                double longitude = Double.parseDouble(truck.get("longitude").toString());
                String starttime = truck.get("starttime").toString();
                String endtime = truck.get("endtime").toString();
                String dayOfWeekStr = truck.get("dayofweekstr").toString();
                String optionaltext = truck.get("optionaltext") == null ? "null" : truck.get("optionaltext").toString();
                String applicant = truck.get("applicant").toString();
                Long locationid = Long.valueOf(truck.get("locationid").toString());
                if(optionaltext.length() > 254) {
                    optionaltext = optionaltext.substring(0,254);
                }

                log.info("Preloading " + repository.save(
                        new Truck(permitLocation,
                                latitude,
                                longitude,
                                starttime,
                                endtime,
                                dayOrder,
                                dayOfWeekStr,
                                optionaltext,
                                applicant,
                                locationid)
                ));
            }
        } catch (IOException e) {
            log.error("Can not find trucks", e);
        }

        JSONArray finalTrucksArray = trucksArray;
        return args -> {
            assert finalTrucksArray != null;
            log.info("Preloading " + finalTrucksArray.size() + " trucks completed!");
            log.info("Day of Week: " + DAY_OF_WEEK[dayOrder] + " !");
        };
    }
}