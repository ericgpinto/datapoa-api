package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.operations.ItineraryClientOperations;
import com.ericpinto.datapoa.model.Coordinates;
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

    private final ItineraryClientOperations itineraryClientUtils;
    private final LineService lineService;

    private final ItineraryRepository itineraryRepository;
    private final LineRepository lineRepository;

    public Itinerary getItineraryByLine(String id) throws JsonProcessingException {
        var itinerary = itineraryClientUtils.mapStringToJson(id);
        var line = lineService.getIdLine(id);

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

    public Itinerary create(Itinerary itinerary, String idLine){
        itinerary.setLine(lineService.getIdLine(idLine));
        return itineraryRepository.insert(itinerary);
    }

    public void delete(String id) {
        findById(id);
        itineraryRepository.deleteById(id);
    }

    public Itinerary udpate(Itinerary itinerary, String id, String idLine) {
        var objItinerary = findById(id);
        objItinerary.setLine(lineService.getIdLine(idLine));
        objItinerary.setCordinatesDetails(itinerary.getCordinatesDetails());

        return itineraryRepository.save(objItinerary);
    }

    public double getLinesByRadius(Double latitude, Double longitude, Double radius){

        Coordinates coordinates = new Coordinates();

        var parseLatitutude = coordinates.getLat();
        var parseLongitude = coordinates.getLng();

        latitude = Double.parseDouble(parseLatitutude);
        longitude = Double.parseDouble(parseLongitude);

        return 6371 * Math.acos(Math.sin(latitude) * Math.cos(longitude));



    }

}
