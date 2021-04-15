package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.BusLine;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusLineRepository extends MongoRepository<BusLine, String> {

    List<BusLine> findByNameContainingIgnoreCase(String name);

    BusLine findByLine(String line);

     List<BusLine> findByCoordenatesNear (Point location, Distance distance);


}
