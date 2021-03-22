package com.ericpinto.datapoa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {

    private Integer id;
//    @GeoSpatialIndexed
    private Double[] coordenates;

}
