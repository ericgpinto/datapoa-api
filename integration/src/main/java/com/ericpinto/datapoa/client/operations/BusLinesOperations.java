package com.ericpinto.datapoa.client.operations;

import com.ericpinto.datapoa.client.BusLineClient;
import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.model.dto.BusLineDTO;
import com.ericpinto.datapoa.model.dto.BusLineItineraryDTO;
import com.ericpinto.datapoa.model.dto.CoordenatesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BusLinesOperations {

    private final BusLineClient busLineClient;
    private final ObjectMapper objectMapper;

    public List<BusLineDTO> getAllLines() throws JsonProcessingException {
        var request = busLineClient.getAllLines();
        return Arrays.asList(objectMapper.readValue(request, BusLineDTO[].class));
    }

    public BusLine getLineWithItinerary(String id) throws JsonProcessingException {
        String request = busLineClient.getItineraryByLine(id);
        var busLineDTO = objectMapper.readValue(request, BusLineItineraryDTO.class);

        return manipulateCoordenates(busLineDTO);
    }


    public BusLine manipulateCoordenates(BusLineItineraryDTO busLineItineraryDTO) {

        BusLine busLine = new BusLine();

        BeanUtils.copyProperties(busLineItineraryDTO, busLine, "cordinatesDetails");

        busLine.setCoordenates(new ArrayList<>());

        for (Map.Entry<String, CoordenatesDTO> busStopDetails : busLineItineraryDTO.getCordinatesDetails().entrySet()) {
            BusLine bl = new BusLine();
            Double[] newDouble = new Double[2];
            newDouble[0] = Double.valueOf(busStopDetails.getValue().getLat());
            newDouble[1] = Double.valueOf(busStopDetails.getValue().getLng());

            bl.setCoordenates(Collections.singletonList(newDouble));

            busLine.getCoordenates().add(newDouble);
        }

        return busLine;

    }

}