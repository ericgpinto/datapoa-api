package com.ericpinto.datapoa.client.operations;

import com.ericpinto.datapoa.client.BusLineClient;
import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.model.BusStop;
import com.ericpinto.datapoa.model.dto.BusLineDTO;
import com.ericpinto.datapoa.model.dto.BusLineItineraryDTO;
import com.ericpinto.datapoa.model.dto.CoordenatesDTO;
import com.ericpinto.datapoa.repository.BusLineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BusLinesOperations {

    private final BusLineClient busLineClient;
    private final BusLineRepository busLineRepository;
    private final ObjectMapper objectMapper;

    public List<BusLineDTO> getAllLines() throws JsonProcessingException {
        var request = busLineClient.getAllLines();
        return Arrays.asList(objectMapper.readValue(request, BusLineDTO[].class));
    }

    public BusLine getLineWithItinerary(String id) throws JsonProcessingException {
        String request = busLineClient.getItineraryByLine(id);
        var busLineDTO = objectMapper.readValue(request, BusLineItineraryDTO.class);

        return populateCoordenates(busLineDTO);
    }

    public BusLine populateCoordenates(BusLineItineraryDTO busLineItineraryDTO){
        BusLine busLine = new BusLine();

        BeanUtils.copyProperties(busLineItineraryDTO, busLine, "cordinatesDetails");

        busLine.setBusStop(new ArrayList<>());

        for (Map.Entry<String, CoordenatesDTO> busStopDetails : busLineItineraryDTO.getCordinatesDetails().entrySet()) {
            BusStop busStop = new BusStop();
            busStop.setId(Integer.valueOf(busStopDetails.getKey()));
            Double[] listCoordenates = new Double[2];
            listCoordenates[0] = Double.valueOf(busStopDetails.getValue().getLat());
            listCoordenates[1] = Double.valueOf(busStopDetails.getValue().getLng());

            busStop.setCoordenates(listCoordenates);

            busLine.getBusStop().add(busStop);

        }

        return busLine;
    }

}
