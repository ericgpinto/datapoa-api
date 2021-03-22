package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.BusLine;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusLineRepository extends MongoRepository<BusLine, String> {

    List<BusLine> findByNameContainingIgnoreCase(String name);

    BusLine findByLine(String line);

//    @Query("{location : {$geoWithin : {$center: [ [point.x, point.y], distance]}}}")
//    List<BusLine> getBusStopByLocationNear(Point location, Distance distance);
//
//    GeoResults<BusLine> findByCoordenatesWithin(Point location, Distance distance);

    List<BusLine> findByCoordenatesNear(Point location, Distance distance);
}
