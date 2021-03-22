package com.ericpinto.datapoa.model.dto;

import com.ericpinto.datapoa.model.BusStop;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
public class LineDTO {

    @JsonProperty("idlinha")
    @NotNull(message = "Line id is required")
    private String idLine;

    @JsonProperty("codigo")
    @NotNull(message = "Code is required")
    private String code;

    @JsonProperty("nome")
    @NotNull(message = "Name is required")
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
