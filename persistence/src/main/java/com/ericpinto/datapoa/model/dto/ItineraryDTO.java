package com.ericpinto.datapoa.model.dto;

import com.ericpinto.datapoa.model.Coordinates;
import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.Line;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ItineraryDTO {

    @Id
    private String id;

    @DBRef
    private Line line;

    private Map<String, Coordinates> cordinatesDetails = new HashMap<>();

}
