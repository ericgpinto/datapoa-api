package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.ItineraryClient;
import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.dto.ItineraryDTO;
import com.ericpinto.datapoa.repository.ItineraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItineraryService {

    private final ItineraryClient itineraryClient;
    private final ObjectMapper objectMapper;
    private final ItineraryRepository itineraryRepository;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        String json = itineraryClient.getItineraryByLine(id);
        var itinerary =  objectMapper.readValue(json, ItineraryDTO.class);
        //return itineraryRepository.save(itinerary);
        System.out.println(itinerary.toString());
        return null;
    }
}
