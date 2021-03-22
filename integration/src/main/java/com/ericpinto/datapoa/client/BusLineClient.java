package com.ericpinto.datapoa.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "buslineclient",
        url = "http://www.poatransporte.com.br/php/facades/process.php",
        configuration = FeignClientEncodingProperties.class)
public interface BusLineClient {

    @GetMapping(value =  "?a=nc&p=%&t=o")
    String getAllLines() throws JsonProcessingException;

    @GetMapping(value = "?a=il")
    String getItineraryByLine(@RequestParam(value = "p") String id);
}
