package com.ericpinto.datapoa.model;

import com.ericpinto.datapoa.model.dto.BusLineDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "buslines")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class BusLine {

    @Id
    private String id;

//    @JsonProperty("id")
    private String line;

//    @JsonProperty("codigo")
    private String code;

//    @JsonProperty("nome")
    private String name;

//    @JsonProperty("busStop")
    private List<BusStop> busStop;

}
