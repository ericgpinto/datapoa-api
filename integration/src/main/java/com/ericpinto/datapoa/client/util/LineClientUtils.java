package com.ericpinto.datapoa.client.util;

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
public class LineClientUtils {

    private final LineClient lineClient;
    private final ObjectMapper objectMapper;

    public List<Line> getAllLines() throws JsonProcessingException {
        String json = lineClient.getAllLines();
        return Arrays.asList(objectMapper.readValue(json, Line[].class));
    }
}
