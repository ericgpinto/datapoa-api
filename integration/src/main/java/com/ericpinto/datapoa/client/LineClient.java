package com.ericpinto.datapoa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "lineclient",
            url = "http://www.poatransporte.com.br/php/facades/process.php",
            configuration = FeignClientEncodingProperties.class)
public interface LineClient {

    @GetMapping(value =  "?a=nc&p=%&t=o")
    String getAllLines();
}
