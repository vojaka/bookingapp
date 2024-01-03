package com.example.bookingapp;

import com.example.bookingapp.controllers.LocationController;
import com.example.bookingapp.controllers.TimeSlotController;
import com.example.bookingapp.entity.Location;
import com.example.bookingapp.entity.TimeSlot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.core.util.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.DataInput;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@WebMvcTest({LocationController.class, TimeSlotController.class})
class BookingappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private LocationController locationController;
    Location location;

    @Test
    void shouldCreateLocation() throws Exception{
         location = createLocation(
                "Kim",
                "String",
                "09:00:00.00",
                "18:00:00.00");

        when(locationController.getLocations()).thenReturn(List.of(location));
        this.mockMvc
                .perform(get("/locations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Kim")))
                .andExpect(jsonPath("$[0].longAddress", is("String")))
                .andExpect(jsonPath("$[0].locationInitialTime", is("09:00:00")))
                .andExpect(jsonPath("$[0].locationEndTime", is("18:00:00")));
//            .andExpect(content().json("{'localTime':'03:45:42.01'}"));
    }

    private Location createLocation(String name, String longAddress, String locationInitialTime, String locationEndTime) {
        long id = 1;
        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime localInitialTime = LocalTime.parse(locationInitialTime,formatter);
        LocalTime localEndTime = LocalTime.parse(locationEndTime,formatter);

        return new Location(id, name, longAddress,localInitialTime, localEndTime);
    }

    @MockBean private TimeSlotController timeSlotController;

    @Test
    void shouldCreateTimeslot() throws Exception{
        String timeslotJSON = ("{ \"startTime\" : \"09:00\",\"endTime\" : \"\",\"startDate\" : \"26-12-2023\", \"endDate\" : \"26-12-2023\", \"isTaken\" : \"false\" }");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.readValue(timeslotJSON, TimeSlot.class);

        mockMvc.perform(post("/location/1/timeslot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeslotJSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                ;


//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        TimeSlot timeSlot = objectMapper.readValue(timeslotJSON, TimeSlot.class);
//        when(timeSlotController.addTimeSlot(1L,timeSlot )).thenReturn((new TimeSlot()));;
//        ResultActions resultActions = this.mockMvc
//                .perform(MockMvcRequestBuilders.post("/location/1/timeslot")
//                        //.param("id","1")
//                        .content(timeslotJSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.id", notNullValue()))
//                //.andExpect(content().json("{ \"startTime\" : \"09:00\",\"endTime\" : \"\",\"startDate\" : \"26-12-2023\", \"endDate\" : \"26-12-2023\", \"isTaken\" : \"false\" }"))
////                .andExpect(jsonPath("startTime", is("09:00")))
////                .andExpect(jsonPath("$[0].endTime", is("10:00")))
////                .andExpect(jsonPath("$[0].startDate", is("26-12-2023")))
////                .andExpect(jsonPath("$[0].endDate", is("26-12-2023")))
////                .andExpect(jsonPath("$[0].isTakes", is(false)))
////                .andExpect(jsonPath("$[0].location_id", is(location.getId())))
//                ;
    }

    @Test
    void shouldGetLocationAndTimeslot() throws Exception {

        when(timeSlotController.getTimeslot()).thenReturn(List.of(new TimeSlot()));
        this.mockMvc
                .perform(get("/timeslot").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].startTime", is("09:00")))
                .andExpect(jsonPath("$[0].endTime", is("10:00")))
                .andExpect(jsonPath("$[0].startDate", is("26-12-2023")))
                .andExpect(jsonPath("$[0].endDate", is("26-12-2023")))
                .andExpect(jsonPath("$[0].isTakes", is(false)))
                .andExpect(jsonPath("$[0].location_id", is(location.getId())));
    }
}
