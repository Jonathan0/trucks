package com.food;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TruckRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Truck("3750 18TH ST", 37.761398102006275, -122.427241066455551)));
            log.info("Preloading " + repository.save(new Truck("505 HOWARD ST", 37.788387888445406, -122.396123930376618)));
        };
    }
}