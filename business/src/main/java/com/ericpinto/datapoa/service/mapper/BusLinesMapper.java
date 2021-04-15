package com.ericpinto.datapoa.service.mapper;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.model.BusLineCustomizedResponse;
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

    public List<BusLineCustomizedResponse> mapToResponse(List<BusLine> busLines){
        List<BusLineCustomizedResponse> busLineCustomizedResponseList = new ArrayList<>();
        busLines.forEach(l -> busLineCustomizedResponseList.add(BusLineCustomizedResponse.builder()
                .id(l.getId())
                .line(l.getLine())
                .code(l.getCode())
                .name(l.getName()).build()));
        return busLineCustomizedResponseList;
    }

}
