package com.ericpinto.datapoa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class Coordinates {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
}
