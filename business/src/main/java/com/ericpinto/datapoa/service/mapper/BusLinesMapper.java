package com.ericpinto.datapoa.service.mapper;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.model.dto.BusLineDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BusLinesMapper {

    public List<BusLine> maptoBusLine(List<BusLineDTO> busLineDTO){
        List<BusLine> lineList = new ArrayList<>();
        busLineDTO.forEach(l -> lineList.add(BusLine.builder()
                .line(l.getLine())
                .code(l.getCode())
                .name(l.getName()).build()));
        return lineList;
    }

}
