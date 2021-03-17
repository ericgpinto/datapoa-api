package com.ericpinto.datapoa.resource;

import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "apidatapoa")
@AllArgsConstructor
public class RestResource {

    private final LineService lineService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/lines")
    public List<Line> findAllLines() throws JsonProcessingException {
        return lineService.getAllLines();
    }


}
