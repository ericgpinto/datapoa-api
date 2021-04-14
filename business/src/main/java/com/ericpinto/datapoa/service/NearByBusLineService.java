package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.model.NearbyBusLineResponse;
import com.ericpinto.datapoa.repository.BusLineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.ericpinto.datapoa.service.mapper.BusLinesMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NearByBusLineService {

    private final BusLinesMapper busLinesMapper;
    private final BusLineRepository busLineRepository;

    public List<NearbyBusLineResponse> findByBusStopsNear(Double lat, Double lng, Double radius){

        Point point = new Point(lat, lng);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);

        var query = busLineRepository.findByCoordenatesNear(point, distance);

        if (query.isEmpty()){
            throw new ObjectNotFoundException("Nenhuma linha encontrada");
        }
        else
            return busLinesMapper.mapToResponse(query);
    }


}
