package com.ericpinto.datapoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @DisplayName("Should be return lines by name")
    void shouldReturn200_WhenFindLinesByName() throws Exception {

        List<Line> lineList = new ArrayList<>();
        lineList.add(line);

        when(lineService.findLineByName(line.getName())).thenReturn(lineList);

        this.mockMvc.perform(get(BASE_URL + "/namesearch?name=1 DE MAIO")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(line))
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be return a user by id")
    void shouldReturn200_WhenFindUserById() throws Exception {
        when(lineService.getLineById("6056d663408d4c49a6dff9bd")).thenReturn(line);

        this.mockMvc.perform(get(BASE_URL + "/6056d663408d4c49a6dff9bd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(line))
        ).andExpect(status().isOk());
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
