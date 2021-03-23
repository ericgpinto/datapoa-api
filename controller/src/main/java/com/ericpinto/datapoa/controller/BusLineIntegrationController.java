package com.ericpinto.datapoa.controller;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.service.BusLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/apidatapoa/callback")
@AllArgsConstructor
public class BusLineIntegrationController {

    private final BusLineService busLineService;

    @ApiOperation("Consome a API datapoa e retorna todas as linhas de ônibus")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 400, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/buslines")
    public List<BusLine> getAllLines() throws JsonProcessingException {
        return busLineService.getAllBusLines();
    }

    @ApiOperation("Consome a API datapoa e retorna o itinerário da linha informada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/itineraries")
    public BusLine getLineWithItinerary(@RequestParam(value = "p") String id) throws JsonProcessingException {
        return busLineService.getLineWithItinerary(id);
    }
}
