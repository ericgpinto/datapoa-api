package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.operations.BusLinesOperations;
import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.repository.BusLineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.ericpinto.datapoa.service.mapper.BusLinesMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusLineService {

    private final BusLineRepository busLineRepository;
    private final BusLinesOperations busLinesOperations;
    private final BusLinesMapper busLinesMapper;

    public List<BusLine> getAllBusLines() throws JsonProcessingException {
        var response = busLinesOperations.getAllLines();
        var mapper = busLinesMapper.maptoBusLine(response);

        if (busLineRepository.count() == 0) {
            return busLineRepository.saveAll(mapper);
        } else
            return mapper;
    }

    public BusLine getLineWithItinerary(String id) throws JsonProcessingException {
        var response = busLinesOperations.getLineWithItinerary(id);
        var find = busLineRepository.findByLine(id);
        Optional<BusLine> lineOptional = Optional.ofNullable(find);

        if (lineOptional.isPresent())
            return update(find.getId(), response);
        throw new ObjectNotFoundException("Linha não encontrada");
    }

    public Page<BusLine> findAll(Pageable pageable) {
        return busLineRepository.findAll(pageable);
    }

    public List<BusLine> getLineByName(String name) {
        return busLineRepository.findByNameContainingIgnoreCase(name);
    }

    public List<BusLine> findByLocatiosnNear( Double latitude, Double longitude, Double radius){
        Point point = new Point(latitude, longitude);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);
        var response = busLineRepository.findByBusStopListNear(point, distance);

        Optional<List<BusLine>> list = Optional.ofNullable(response);

        if (list.isPresent()) {
            return response;
        }

        throw new ObjectNotFoundException("Não foram encontradas linhas de ônibus com os dados informados.");

    }

    public BusLine createNewBusLine(BusLine busLine) {
        var find = busLineRepository.findByLine(busLine.getLine());

        Optional<BusLine> optionalBusLine = Optional.ofNullable(find);

        if (optionalBusLine.isPresent()) {
            return update(find.getId(), busLine);
        }

        return busLineRepository.insert(busLine);
    }

    public BusLine update(String id, BusLine busLine) {
        var obj = findById(id);

        obj.setLine(busLine.getLine());
        obj.setCode(busLine.getCode());
        obj.setName(busLine.getName());
        obj.setBusStopList(busLine.getBusStopList());

        return busLineRepository.save(obj);
    }

    public BusLine findById(String id) {
        return busLineRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Linha de onibus não encontrada"));
    }

    public void delete(String id) {
        findById(id);
        busLineRepository.deleteById(id);
    }

}
