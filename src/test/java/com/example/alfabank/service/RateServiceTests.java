package com.example.alfabank.service;

import com.example.alfabank.client.RatesFeignClient;
import com.example.alfabank.service.impl.RateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static com.example.alfabank.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RateServiceTests {

    @Autowired
    private RateServiceImpl rateService;
    @MockBean
    private RatesFeignClient feignClient;

    @Test
    void getRate_correctCurrency_negativeDifferentialReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(getLowerRate());
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(getHigherRate());

        Double rate = rateService.getRate(RUB);

        assertTrue(rate < 0);
    }

    @Test
    void getRate_correctCurrency_positiveDifferentialReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(getHigherRate());
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(getLowerRate());

        Double rate = rateService.getRate(RUB);

        assertTrue(rate > 0);
    }

    @Test
    void getRate_incorrectCurrency_NaNReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(getLowerRate());
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(getHigherRate());

        Double rate = rateService.getRate(RRR);

        assertTrue(rate.isNaN());
    }

    @Test
    void getRate_correctCurrency_zeroDifferentialReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(getLowerRate());
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(getLowerRate());

        Double rate = rateService.getRate(RUB);

        assertEquals(0, rate);
    }

    @Test
    void getRate_getLatestReturnedNull_NaNReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(null);
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(getHigherRate());

        Double rate = rateService.getRate(RUB);

        assertTrue(rate.isNaN());
    }

    @Test
    void getRate_getHistoricalReturnedNull_NaNReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(getLowerRate());
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(null);

        Double rate = rateService.getRate(RUB);

        assertTrue(rate.isNaN());
    }

    @Test
    void getRate_getLatestAndHistoricalReturnedNull_NaNReturned() {
        when(feignClient.getLatest(anyString())).thenReturn(null);
        when(feignClient.getHistorical(anyString(), anyString())).thenReturn(null);

        Double rate = rateService.getRate(RUB);

        assertTrue(rate.isNaN());
    }
}
