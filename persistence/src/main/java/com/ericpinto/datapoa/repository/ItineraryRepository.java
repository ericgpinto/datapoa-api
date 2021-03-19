package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

    String findItineraryByIdLine(String idLine);
}
