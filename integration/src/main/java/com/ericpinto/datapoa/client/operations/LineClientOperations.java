package com.ericpinto.datapoa.client.operations;

import com.ericpinto.datapoa.client.LineClient;
import com.ericpinto.datapoa.model.Line;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class LineClientOperations {

    private final LineClient lineClient;
    private final ObjectMapper objectMapper;

    public List<Line> mapStringToJson() throws JsonProcessingException {
        String json = lineClient.getAllLines();
        return Arrays.asList(objectMapper.readValue(json, Line[].class));
    }
}