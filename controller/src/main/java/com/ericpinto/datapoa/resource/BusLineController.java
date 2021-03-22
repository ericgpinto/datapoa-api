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
    @GetMapping(value = "/{id}")
    public BusLine findById(@PathVariable String id){
        return busLineService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "itineraries/callback")
    public BusLine getLineWithItinerary(@RequestParam(value = "p") String id) throws JsonProcessingException {
        return busLineService.getLineWithItinerary(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "namesearch")
    public List<BusLine> getLineByName(@RequestParam(value = "name") String name){
        return busLineService.getLineByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public BusLine createNewBusLine(@RequestBody BusLine busLine){
        return busLineService.createNewBusLine(busLine);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable String id){
        busLineService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "udpate/{id}")
    public BusLine update(@PathVariable String id, @RequestBody BusLine busLine){
        return busLineService.update(id, busLine);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/radiussearch")
    public List<BusLine> findLineByLocationNear(
            @RequestParam(value = "lng") Double longitude,
            @RequestParam(value = "lat") Double latitude,
            @RequestParam(value = "radius") Double radius){

        return busLineService.findByCoordenatesWithin(longitude, latitude, radius);
    }

}
