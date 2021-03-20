package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.util.ItineraryClientUtils;
import com.ericpinto.datapoa.client.util.LineClientUtils;
import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.repository.ItineraryRepository;
import com.ericpinto.datapoa.repository.LineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItineraryService {

    private final ItineraryClientUtils itineraryClientUtils;
    private final LineService lineService;

    private final ItineraryRepository itineraryRepository;
    private final LineRepository lineRepository;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        var itinerary = itineraryClientUtils.getItineraryByLine(id);
        var line = lineService.getLineById(id);

        if (line == null){
            throw new ObjectNotFoundException("Linha não encontrada");
        }
        else if (itineraryRepository.findByLine(line) == null){
                itinerary.setLine(line);
                return itineraryRepository.save(itinerary);
        } else {
            itinerary.setLine(line);
            return itinerary;
        }
    }


    public Itinerary findById(String id){
        return itineraryRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Itinerario não encontrado"));
    }
}
