package com.ericpinto.datapoa.client.operations;

import com.ericpinto.datapoa.client.ItineraryClient;
import com.ericpinto.datapoa.model.Itinerary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItineraryClientOperations {

    private final ObjectMapper objectMapper;
    private final ItineraryClient itineraryClient;

    public Itinerary mapStringToJson(String id) throws JsonProcessingException {
        String request = itineraryClient.getItineraryByLine(id);
        return objectMapper.readValue(request, Itinerary.class);
    }

}
