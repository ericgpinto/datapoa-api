package com.ericpinto.datapoa.repository;

import com.ericpinto.datapoa.model.Line;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends MongoRepository<Line, String> {
    @CountQuery
    @Query(value = "[{$count:'name'}]", count = true)

    List<Line> findByNameContainingIgnoreCase(String name);

}
