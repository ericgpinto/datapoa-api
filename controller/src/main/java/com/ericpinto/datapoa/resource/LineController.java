package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.resource.util.URL;
import com.ericpinto.datapoa.service.ItineraryService;
import com.ericpinto.datapoa.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @GetMapping(value = "/callback")
    public List<Line> findAllLines() throws JsonProcessingException {
        return lineService.getAllLines();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-all")
    public Page<Line> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable pageable;
        pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("_id")));
        return lineService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Line getLineById(@PathVariable String id){
        return lineService.getLineById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/namesearch")
    public List<Line> findByName(@RequestParam(value="name", defaultValue="") String name) {
        name = URL.decodeParam(name);
        return lineService.findLineByName(name);
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
