package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusLineRepository extends MongoRepository<BusLine, String> {

    BusLine findByLine(String line);

//    @Query("{'busStop.coordenates' :{ $near : [ ?1 , ?2], $maxDistance: ?3*1000}}")
//    List<BusLine> findByLocationNear(Double longitude, Double latitude, Double radius);
}
