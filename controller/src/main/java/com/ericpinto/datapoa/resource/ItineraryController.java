package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.service.ItineraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apidatapoa/itineraries")
@AllArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/callback")
    public Itinerary getItineraryByLine(@RequestParam(value = "p") String id) throws JsonProcessingException {
        return itineraryService.getItineraryByLine(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-all")
    public Page<Line> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable pageable;
        pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("_id")));
        return itineraryService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}")
    public Itinerary findById(@PathVariable String id){
        return itineraryService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create/{idLine}")
    public Itinerary create(@RequestBody Itinerary itinerary, @PathVariable String idLine){
        return itineraryService.create(itinerary, idLine);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable String id){
        itineraryService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update/{id}")
    public Itinerary update(@RequestBody Itinerary itinerary, @PathVariable String id,  @RequestParam(value = "idLine") String idLine){
        return itineraryService.udpate(itinerary, id, idLine);
    }

}
