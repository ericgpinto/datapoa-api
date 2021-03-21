package com.ericpinto.datapoa;

import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.resource.LineController;
import com.ericpinto.datapoa.service.ItineraryService;
import com.ericpinto.datapoa.service.LineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = LineController.class)
class LinesEndpointsTests {

    private final String BASE_URL = "/apidatapoa/lines";

    @MockBean
    private LineService lineService;

    @MockBean
    private ItineraryService itineraryService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Line line = Line.builder()
            .identifier("6056d663408d4c49a6dff9bd")
            .idLine("5517")
            .code("250-1")
            .name("1 DE MAIO").build();

    @Test
    @DisplayName("Should return 201 when create user")
    void shouldReturn201_whenCreateLine() throws Exception {
        when(lineService.createLine(line)).thenReturn(line);
        mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(line))
        ).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should be update a line")
    void shouldReturn200_WhenUpdateLine() throws Exception {

        when(lineService.getLineById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(line);
        when(lineService.update("5fc7ba0ee7e48d20dc2fbf52", line)).thenReturn(line);

        this.mockMvc.perform(put(BASE_URL + "/update/6056d663408d4c49a6dff9bd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(line)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be delete a line")
    void shouldReturn204_WhenDeleteUser() throws Exception {
        this.mockMvc.perform(delete(BASE_URL + "/delete/6056d663408d4c49a6dff9bd"))
                .andExpect(status().isNoContent());
    }
}
