package com.ericpinto.datapoa.model;

import com.ericpinto.datapoa.model.dto.BusLineDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "buslines")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties("index")
public class BusLine {

    @Id
    @ApiModelProperty(value = "Id unico")
    private String id;

    @ApiModelProperty(value = "Id da linha de ônibus")
    private String line;

    @ApiModelProperty(value = "Código da linha de ônibus")
    private String code;

    @ApiModelProperty(value = "Nome da linha de ônibus")
    private String name;

    @ApiModelProperty(value = "Index da coordenada")
    private Integer index;

    @ApiModelProperty(value = "Itinerário da linha de ônibus")
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Double[] coordenates;

}
