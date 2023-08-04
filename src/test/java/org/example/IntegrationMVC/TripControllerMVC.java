package org.example.IntegrationMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class TripControllerMVC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void tripController_CreateTrip() throws Exception {
        NewTripDTO tripDTO = new NewTripDTO();
        tripDTO.tripId = new TripId(1L);
        tripDTO.origCity = new City("Orleans");
        tripDTO.destCity = new City("Miami");
        tripDTO.departure = LocalDate.of(2023, 01, 10);
        tripDTO.arrival = LocalDate.of(2023, 01, 20);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/Trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent1 = result.getResponse().getContentAsString();
        assertNotNull(resultContent1);
        System.out.println(resultContent1);
    }

}
