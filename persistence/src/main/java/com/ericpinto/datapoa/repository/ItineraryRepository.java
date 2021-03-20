package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.Line;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

    Line findByLine(Line line);
}
