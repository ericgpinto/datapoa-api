package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.service.ItineraryService;
import com.ericpinto.datapoa.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "apidatapoa")
@AllArgsConstructor
public class RestResource {

    private final LineService lineService;
    private final ItineraryService itineraryService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(value = "lines/create")
//    public Line createLine(){}

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/lines")
    public List<Line> findAllLines() throws JsonProcessingException {
        return lineService.getAllLines();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/lines/{id}")
    public Line getLineById(@PathVariable String id){
        return lineService.getLineById(id);
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping(value = "lines/delete")
//    public void delete(@PathVariable String id){}
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping(value = "/lines/update")
//    public Line updateLine(@PathVariable String id){}


}
