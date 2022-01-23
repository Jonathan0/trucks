package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.Truck;
import com.food.TruckRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(TruckControllerTest.class)
public class TruckControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    TruckRepository truckRepository;

    Truck Truck_1 = new Truck("3750 18TH ST", 37.77799350505247,-122.41855163440088, "11AM", "7PM", "Ice Cream");
    Truck Truck_2 = new Truck("50 IVY ST", 37.75410782820197, -122.4131684456666, "2PM", "9PM", "coffee");
    Truck Truck_3 = new Truck("3055 23RD ST", 37.79284659513321, -122.40236649693905, "9AM", "12PM", "Fruit drink");

    // @Test
    public void getAllRecords_success() throws Exception {
        List<Truck> records = new ArrayList<>(Arrays.asList(Truck_1, Truck_2, Truck_3));

        Mockito.when(truckRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trucks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].permitLocation", is("3055 23RD ST")));
    }

    // @Test
    public void getPatientById_success() throws Exception {
        Mockito.when(truckRepository.findById(Truck_1.getId())).thenReturn(java.util.Optional.of(Truck_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trucks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.permitLocation", is("3750 18TH ST")));
    }
}
