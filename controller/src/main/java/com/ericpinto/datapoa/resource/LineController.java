package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.service.ItineraryService;
import com.ericpinto.datapoa.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "apidatapoa/lines")
@AllArgsConstructor
public class LineController {

    private final LineService lineService;
    private final ItineraryService itineraryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public Line createLine(@Valid @RequestBody Line line){
        return lineService.createLine(line);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Line> findAllLines() throws JsonProcessingException {
        return lineService.getAllLines();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Line getLineById(@PathVariable String id){
        return lineService.getLineById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable String id){
        lineService.delete(id);
    }
//
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update/{id}")
    public Line updateLine(@PathVariable String id, @RequestBody Line line){
        return lineService.update(id, line);
    }

}
