package com.ericpinto.datapoa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "lines")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    @Id
    //@NotNull(message = "Identifier is required")
    private String identifier;

    @JsonProperty("id")
    @NotNull(message = "Line id is required")
    private String idLine;

    @JsonProperty("codigo")
    @NotNull(message = "Code is required")
    private String code;

    @JsonProperty("nome")
    @NotNull(message = "Name is required")
    private String name;
}
