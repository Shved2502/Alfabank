package com.example.alfabank.service.impl;

import com.example.alfabank.client.FeignClient;
import com.example.alfabank.model.Rate;
import com.example.alfabank.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final FeignClient feignClient;
    @Value("${openexchangerates.app.id}")
    private String appId;
    @Value("${openexchangerates.base}")
    private String base;

    @Override
    public Rate getRate() {
        return feignClient.getLatest(appId);
    }
}
