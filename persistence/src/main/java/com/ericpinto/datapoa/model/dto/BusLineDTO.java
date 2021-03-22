package com.ericpinto.datapoa.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusLineDTO {

    @JsonProperty("id")
    @NotNull(message = "Line id is required")
    private String line;

    @JsonProperty("codigo")
    @NotNull(message = "Code is required")
    private String code;

    @JsonProperty("nome")
    @NotNull(message = "Name is required")
    private String name;
}
