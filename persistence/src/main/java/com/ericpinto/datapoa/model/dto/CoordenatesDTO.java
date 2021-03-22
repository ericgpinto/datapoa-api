package com.ericpinto.datapoa.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoordenatesDTO {

    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
}
