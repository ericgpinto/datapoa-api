package com.ericpinto.datapoa.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "itinerary")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"idlinha", "nome", "codigo"})
public class Itinerary {

    @Id
    private String id;

    @DBRef
    private Line line;

    private Map<String, Coordinates> cordinatesDetails = new HashMap<>();

    @JsonAnyGetter
    public Map<String,Coordinates> getCoordenates() {
        return cordinatesDetails;
    }

    @JsonAnySetter
    public void setCoordenates(String key, Coordinates value) {
        cordinatesDetails.put(key, value);
    }
}
