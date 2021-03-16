package com.ericpinto.tecnocorp.client;

import com.ericpinto.datapoa.model.Line;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class LineClientImpl implements LineClient{

    @Value("${datapoa.URI}")
    private final String datapoaURI;

    private final RestTemplate restTemplate;

    public List<Line> getAllLines() {
        ResponseEntity<Line> response = restTemplate.getForEntity(datapoaURI, Line.class);
        return Arrays.asList(response.getBody());
    }
}
