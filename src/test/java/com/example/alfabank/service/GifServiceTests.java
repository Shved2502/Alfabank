package com.example.alfabank.service;

import com.example.alfabank.client.GifsFeignClient;
import com.example.alfabank.service.impl.GifServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.alfabank.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class GifServiceTests {

    @Autowired
    private GifServiceImpl gifService;
    @MockBean
    private GifsFeignClient feignClient;

    @Test
    void getGifURI_positiveDifferential_richURIReturned() {
        when(feignClient.getGif(anyString(), anyString())).thenReturn(getRichGif());

        String gifURI = gifService.getGifURI(1.0);

        assertEquals(RICH_URI, gifURI);
        assertNotEquals(BROKE_URI, gifURI);
        assertNotEquals(ZERO_URI, gifURI);
        assertNotEquals(FAIL_URI, gifURI);
    }

    @Test
    void getGifURI_negativeDifferential_brokeURIReturned() {
        when(feignClient.getGif(anyString(), anyString())).thenReturn(getBrokeGif());

        String gifURI = gifService.getGifURI(-1.0);

        assertEquals(BROKE_URI, gifURI);
        assertNotEquals(RICH_URI, gifURI);
        assertNotEquals(ZERO_URI, gifURI);
        assertNotEquals(FAIL_URI, gifURI);
    }

    @Test
    void getGifURI_zeroDifferential_zeroURIReturned() {
        when(feignClient.getGif(anyString(), anyString())).thenReturn(getZeroGif());

        String gifURI = gifService.getGifURI(0.0);

        assertEquals(ZERO_URI, gifURI);
        assertNotEquals(BROKE_URI, gifURI);
        assertNotEquals(RICH_URI, gifURI);
        assertNotEquals(FAIL_URI, gifURI);
    }

    @Test
    void getGifURI_NaNDifferential_failURIReturned() {
        when(feignClient.getGif(anyString(), anyString())).thenReturn(getFailGif());

        String gifURI = gifService.getGifURI(Double.NaN);

        assertEquals(FAIL_URI, gifURI);
        assertNotEquals(BROKE_URI, gifURI);
        assertNotEquals(ZERO_URI, gifURI);
        assertNotEquals(RICH_URI, gifURI);
    }
}
