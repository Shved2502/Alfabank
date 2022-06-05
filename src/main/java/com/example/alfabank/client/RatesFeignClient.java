package com.example.alfabank.client;

import com.example.alfabank.model.Rate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "rate", url = "${openexchangerates.url}")
public interface RatesFeignClient {

    @GetMapping("/latest.json")
    Rate getLatest(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}")
    Rate getHistorical(@PathVariable String date, @RequestParam("app_id") String appId);
}
