package com.ericpinto.datapoa.controller;

import com.ericpinto.datapoa.controller.util.URL;
import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.model.BusLineCustomizedResponse;
import com.ericpinto.datapoa.service.BusLineService;
import com.ericpinto.datapoa.service.NearbyBusLineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "apidatapoa/buslines")
@AllArgsConstructor
public class BusLineController {

    private final BusLineService busLineService;
    private final NearbyBusLineService nearLocationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    @ApiOperation("Busca por todas as linhas de õnibus armazenadas no banco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public Page<BusLine> getAllBusLines(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable pageable;
        pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        return busLineService.findAll(pageable);
    }

    @ApiOperation("Retorna a linha de ônibus de acordo com o id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public BusLine findById(@PathVariable String id){
        return busLineService.findById(id);
    }

    @ApiOperation("Retorna uma lista de linhas de ônibus de acordo com o nome informado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "namesearch")
    public List<BusLine> getLineByName(@RequestParam(value = "name") String name){
        name = URL.decodeParam(name);
        return busLineService.getLineByName(name);
    }

    @ApiOperation("Cria uma nova linha de ônibus")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "create")
    public BusLine createNewBusLine(@RequestBody BusLine busLine){
        return busLineService.createNewBusLine(busLine);
    }

    @ApiOperation("Exclui uma linha de ônibus de acordo com o id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable String id){
        busLineService.delete(id);
    }

    @ApiOperation("Atualiza uma linha de ônibus de acordo com o id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "update/{id}")
    public BusLine update(@PathVariable String id, @RequestBody BusLine busLine){
        return busLineService.update(id, busLine);
    }

    @ApiOperation("Busca todas as linhas de ônibus dentro das coordenadas e do raio informado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/nearby-busline-search")
    public List<BusLineCustomizedResponse> findLineByLocationNear(
            @RequestParam(value = "lat") Double longitude,
            @RequestParam(value = "lng") Double latitude,
            @RequestParam(value = "radius") Double radius){

        return nearLocationService.findByBusStopsNear(latitude, longitude, radius);
    }

}
