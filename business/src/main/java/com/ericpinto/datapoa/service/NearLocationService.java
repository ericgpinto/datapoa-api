package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NearLocationService {

    private final MongoTemplate template;

    public List<BusLine> findByBusStopsNear(Double lat, Double lng, Double radius){

        Point point = new Point(lat, lng);

        Optional<List<BusLine>> lineList = Optional.of(
                template.find(new Query(Criteria.where("coordenates")
                        .nearSphere(point)
                        .maxDistance(radius)), BusLine.class));

        return lineList.orElseThrow(() -> new ObjectNotFoundException("Nenhuma linha de ônibus encontrada"));

    }


}
