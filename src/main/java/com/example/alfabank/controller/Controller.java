package com.example.alfabank.controller;

import com.example.alfabank.model.Rate;
import com.example.alfabank.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final RateService rateService;

    @GetMapping("/result")
    ResponseEntity<Rate> getRate() {
        return ResponseEntity.ok(rateService.getRate());
    }
}
