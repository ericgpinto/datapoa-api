package com.ericpinto.datapoa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends MongoRepository<Line, String> {

    List<Line> findByNameContainingIgnoreCase(String name);

    Line findByIdLine(String idLine);

}
