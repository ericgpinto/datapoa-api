package com.ericpinto.datapoa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BusLineCustomizedResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("line")
    private String line;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;
}
