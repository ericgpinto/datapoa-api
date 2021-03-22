package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.service.BusLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "apidatapoa/buslines")
@AllArgsConstructor
public class BusLineController {

    private final BusLineService busLineService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/callback")
    public List<BusLine> findAll() throws JsonProcessingException {
        return busLineService.getAllBusLines();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("itineraries/callback")
    public BusLine getItineraryByLine(@RequestParam(value = "p") String id) throws JsonProcessingException {
        return busLineService.getLineWithItinerary(id);
    }

}