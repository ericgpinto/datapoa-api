package com.ericpinto.datapoa.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "itinerary")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Itinerary {

    @Id
    @JsonProperty("idlinha")
    private String idLine;

    @JsonProperty("codigo")
    @NotNull(message = "Id is required")
    private String code;

    @JsonProperty("nome")
    @NotNull(message = "name is required")
    private String name;

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
