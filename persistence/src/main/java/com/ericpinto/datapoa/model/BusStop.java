package com.ericpinto.datapoa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {

    private Integer id;
    private Double[] coordenates;

}
