package com.ericpinto.datapoa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "itineraryclient",
        url = "http://www.poatransporte.com.br/php/facades/process.php")

public interface ItineraryClient {
    @GetMapping(value = "?a=il")
    String getItineraryByLine(@RequestParam(value = "p") String id);
}
