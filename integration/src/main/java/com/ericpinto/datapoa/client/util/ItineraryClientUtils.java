package com.ericpinto.datapoa.client.util;

import com.ericpinto.datapoa.client.ItineraryClient;
import com.ericpinto.datapoa.model.Itinerary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItineraryClientUtils {
    private final ItineraryClient itineraryClient;
    private final ObjectMapper objectMapper;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        String json = itineraryClient.getItineraryByLine(id);
        return objectMapper.readValue(json, Itinerary.class);
    }
}
