package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.util.ItineraryClientUtils;
import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.repository.ItineraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItineraryService {

    private final ItineraryClientUtils itineraryClientUtils;
    private final ItineraryRepository itineraryRepository;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        var itinerary = itineraryClientUtils.getItineraryByLine(id);
        BeanUtils.copyProperties(itinerary, Itinerary.class);
        return itineraryRepository.save(itinerary);
    }
}
