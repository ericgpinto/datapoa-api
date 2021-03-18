package com.ericpinto.datapoa.model.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ItineraryDTO {

    @JsonProperty("idlinha")
    private String idLine;

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("nome")
    private String name;

    @JsonAnySetter
    private Map<String, CoordinatesDTO> cordinatesDetails = new HashMap<>();

    public String getIdLine() {
        return idLine;
    }

    public void setIdLine(String idLine) {
        this.idLine = idLine;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CoordinatesDTO> getCordinatesDetails() {
        return cordinatesDetails;
    }

    public void setCordinatesDetails(Map<String, CoordinatesDTO> cordinatesDetails) {
        this.cordinatesDetails = cordinatesDetails;
    }

    @Override
    public String toString() {
        return "ItineraryDTO{" +
                "idLine='" + idLine + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cordinatesDetails=" + cordinatesDetails +
                '}';
    }
}
