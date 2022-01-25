package com;

import com.food.TruckRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com")
public class MockConfig {
    @Bean
    @Primary
    public TruckRepository TruckMock() {
        TruckRepository mockTruckRepository = Mockito.mock(TruckRepository.class);
        return mockTruckRepository;
    }
}
