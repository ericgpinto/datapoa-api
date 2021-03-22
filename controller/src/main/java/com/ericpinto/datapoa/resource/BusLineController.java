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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("namesearch")
    public List<BusLine> getLineByName(@RequestParam(value = "name") String name){
        return busLineService.getLineByName(name);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/radiussearch/{radius}")
//    public List<BusLine> findLineByLocationNear(
//            @RequestParam(value = "lgn") Double longitude,
//            @RequestParam(value = "lat") Double latitude,
//            @PathVariable Double radius){
//
//        return busLineService.findLineByLocationNear(longitude, latitude, radius);
//    }

}
