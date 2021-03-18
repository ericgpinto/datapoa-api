package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Itinerary;
import com.ericpinto.datapoa.service.ItineraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apidatapoa/itinerary")
@AllArgsConstructor
public class ItineraryController {

    private ItineraryService itineraryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Itinerary getItineraryByLine(@RequestParam(value = "p") String id) throws JsonProcessingException {
        return itineraryService.getItineraryByLine(id);
    }
}
