package org.example.IntegrationMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.valueobjects.City;
import org.example.domain.valueobjects.TripId;
import org.example.dto.NewTripDTO;
import org.example.dto.PeopleDTO;
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
        //Adds first Trip
        NewTripDTO tripDTO = new NewTripDTO();
        tripDTO.tripId = new TripId(1L);
        tripDTO.origCity = new City("Orleans");
        tripDTO.destCity = new City("Miami");
        tripDTO.departure = LocalDate.of(2023, 01, 10);
        tripDTO.arrival = LocalDate.of(2023, 01, 20);

        MvcResult resultCreated = mockMvc
                .perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent1 = resultCreated.getResponse().getContentAsString();
        assertNotNull(resultContent1);
        // Tries to add the same Trip, should fail
        MvcResult resultAlreadyExists = mockMvc
                .perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent2 = resultAlreadyExists.getResponse().getContentAsString();
        assertNotNull(resultContent2);
        System.out.println(resultContent1);
    }

    @Test
    void tripController_AddPeopleToTrip() throws Exception {
        //Adds a trip to database
        NewTripDTO tripDTO = new NewTripDTO();
        tripDTO.tripId = new TripId(3L);
        tripDTO.origCity = new City("Orleans");
        tripDTO.destCity = new City("Miami");
        tripDTO.departure = LocalDate.of(2023, 01, 10);
        tripDTO.arrival = LocalDate.of(2023, 01, 20);

        MvcResult resultCreated = mockMvc
                .perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.firstName = "Joao";
        peopleDTO.lastName = "Luis";

        //Add first person to trip
        String tripId = "3";

        MvcResult resultAdded = mockMvc
                .perform(MockMvcRequestBuilders.patch("/Trips/{tripId}/people", tripId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(peopleDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent1 = resultAdded.getResponse().getContentAsString();
        assertNotNull(resultContent1);


        //Try to add the same person again
        MvcResult resultAlreadyAdded = mockMvc
                .perform(MockMvcRequestBuilders.patch("/Trips/{tripId}/people", tripId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(peopleDTO))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent2 = resultAlreadyAdded.getResponse().getContentAsString();
        assertNotNull(resultContent2);
        System.out.println(resultContent1);
    }

    @Test
    void tripController_DeleteATrip() throws Exception {
        //Adds a trip to database
        NewTripDTO tripDTO = new NewTripDTO();
        tripDTO.tripId = new TripId(4L);
        tripDTO.origCity = new City("Orleans");
        tripDTO.destCity = new City("Miami");
        tripDTO.departure = LocalDate.of(2023, 01, 10);
        tripDTO.arrival = LocalDate.of(2023, 01, 20);

        MvcResult resultCreated = mockMvc
                .perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.firstName = "Joao";
        peopleDTO.lastName = "Luis";

        String tripId = "4";
        //Deletes said Trip
        MvcResult resultDeleted = mockMvc
                .perform(MockMvcRequestBuilders.delete("/trips/{tripId}", tripId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent1 = resultDeleted.getResponse().getContentAsString();
        assertNotNull(resultContent1);

        //Tries to delete already deleted Trip
        MvcResult resultnotFound = mockMvc
                .perform(MockMvcRequestBuilders.delete("/trips/{tripId}", tripId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent2 = resultnotFound.getResponse().getContentAsString();
        assertNotNull(resultContent2);
    }

    @Test
    void tripController_ListAllTrips() throws Exception {
        //Tries to get trips on an empty DB
        MvcResult resultNotFound = mockMvc
                .perform(MockMvcRequestBuilders.get("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent1 = resultNotFound.getResponse().getContentAsString();
        assertNotNull(resultContent1);

        //Adds a trip to database
        NewTripDTO tripDTO = new NewTripDTO();
        tripDTO.tripId = new TripId(5L);
        tripDTO.origCity = new City("Orleans");
        tripDTO.destCity = new City("Miami");
        tripDTO.departure = LocalDate.of(2023, 01, 10);
        tripDTO.arrival = LocalDate.of(2023, 01, 20);

        MvcResult resultCreated = mockMvc
                .perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripDTO))
                )
                .andExpect(status().isOk())
                .andReturn();

        //Queries populated table for trips
        MvcResult resultFound = mockMvc
                .perform(MockMvcRequestBuilders.get("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent2 = resultFound.getResponse().getContentAsString();
        assertNotNull(resultContent2);
    }
}
