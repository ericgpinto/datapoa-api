package com.ericpinto.datapoa.model.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusLineItineraryDTO {

    @JsonProperty("idlinha")
    private String line;

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("nome")
    private String name;

    private Map<String, CoordenatesDTO> cordinatesDetails = new HashMap<>();

    @JsonAnyGetter
    public Map<String, CoordenatesDTO> getCoordenates() {
        return cordinatesDetails;
    }

    @JsonAnySetter
    public void setCoordenates(String key, CoordenatesDTO value) {
        cordinatesDetails.put(key, value);
    }
}
