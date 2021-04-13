package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.operations.BusLinesOperations;
import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.repository.BusLineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.ericpinto.datapoa.service.mapper.BusLinesMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusLineIntegrationService {
    private final BusLineRepository busLineRepository;
    private final BusLinesOperations busLinesOperations;
    private final BusLinesMapper busLinesMapper;
    private final BusLineService busLineService;

    public List<BusLine> getAllBusLines() throws JsonProcessingException {
        var response = busLinesOperations.getAllLines();
        var mapper = busLinesMapper.maptoBusLine(response);

        if (busLineRepository.count() == 0) {
            return busLineRepository.saveAll(mapper);
        } else
            return mapper;
    }

    public BusLine getItineraryByLine(String id) throws JsonProcessingException {
        var response = busLinesOperations.getLineWithItinerary(id);
        var find = busLineRepository.findByLine(id);
        Optional<BusLine> lineOptional = Optional.ofNullable(find);

        

        if (lineOptional.isPresent()) {
            //response.setBusStops(find.getBusStops());
            response.setCoordenates(find.getCoordenates());
//
            return busLineService.update(find.getId(), response);
        }
        throw new ObjectNotFoundException("Linha não encontrada");
    }
}
