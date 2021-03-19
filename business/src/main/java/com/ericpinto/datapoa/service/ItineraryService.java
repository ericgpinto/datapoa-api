package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.util.ItineraryClientUtils;
import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.repository.ItineraryRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItineraryService {

    private final ItineraryClientUtils itineraryClientUtils;
    private final ItineraryRepository itineraryRepository;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        var itinerary = itineraryClientUtils.getItineraryByLine(id);
        BeanUtils.copyProperties(itinerary, Itinerary.class);
        var find = itineraryRepository.findItineraryByIdLine(itinerary.getIdLine());
        if (find == null){
            itineraryRepository.save(itinerary);
            return itinerary;
        }
        else return itinerary;

    }

    public Itinerary findById(String id){
        return itineraryRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Itinerario não encontrado"));
    }
}
