package com.example.alfabank.controller;

import com.example.alfabank.service.GifService;
import com.example.alfabank.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final RateService rateService;
    private final GifService gifService;

    @GetMapping("/result/{currency}")
    ResponseEntity<Void> getRate(@PathVariable String currency) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(gifService.getGifURI(rateService.getRate(currency))))
                .build();
    }
}
