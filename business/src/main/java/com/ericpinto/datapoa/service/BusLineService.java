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

@Service
@AllArgsConstructor
public class BusLineService {

    private final BusLineRepository busLineRepository;
    private final BusLinesOperations busLinesOperations;
    private final BusLinesMapper busLinesMapper;

    public List<BusLine> getAllBusLines() throws JsonProcessingException {
        var response = busLinesOperations.getAllLines();
        var mapper = busLinesMapper.maptoBusLine(response);

        if (busLineRepository.count() == 0){
            return busLineRepository.saveAll(mapper);
        }
        else
            return this.findAll();
    }

    public BusLine getLineWithItinerary(String id) throws JsonProcessingException {
        var response = busLinesOperations.getLineWithItinerary(id);

        var find = busLineRepository.findByLine(id);

        if (find == null)
            throw new ObjectNotFoundException("Linha não encontrada");
        else
            return update(find.getIdentity(), response, id);
    }

    public BusLine update(String id, BusLine busLine, String idLine){
        BusLine obj = findById(id);

        obj.setLine(busLine.getLine());
        obj.setCode(busLine.getCode());
        obj.setName(busLine.getName());
        obj.setBusStop(busLine.getBusStop());

        return busLineRepository.save(obj);

    }


    public List<BusLine> findAll(){
        return busLineRepository.findAll();
    }

    public BusLine findById(String id){
        return busLineRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Linha de onibus não encontrada"));
    }
}
