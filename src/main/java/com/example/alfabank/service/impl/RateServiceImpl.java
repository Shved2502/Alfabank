package com.example.alfabank.service.impl;

import com.example.alfabank.client.RatesFeignClient;
import com.example.alfabank.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RatesFeignClient ratesFeignClient;
    @Value("${openexchangerates.app.id}")
    private String appId;

    @Override
    public Double getRate(String currency) {
        StringBuilder date = new StringBuilder(LocalDate.now().minusDays(1).toString());
        date.append(".json");
        try {
            Map<String, Double> yesterdayRates = ratesFeignClient.getHistorical(date.toString(), appId).getRates();
            Map<String, Double> todayRates = ratesFeignClient.getLatest(appId).getRates();
            Double yesterdayValue = yesterdayRates.get(currency);
            Double todayValue = todayRates.get(currency);
            return todayValue - yesterdayValue;
        } catch (NullPointerException e) {
            return Double.NaN;
        }
    }
}
