package com.example.alfabank.controller;

import com.example.alfabank.service.GifService;
import com.example.alfabank.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.alfabank.utils.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
class ControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private RateService rateService;
    @MockBean
    private GifService gifService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getRate_positiveDifferential_redirectedToRichURI() throws Exception {
        when(rateService.getRate(RUB)).thenReturn(1.0);
        when(gifService.getGifURI(1.0)).thenReturn(RICH_URI);

        mockMvc.perform(get("/api/v1/result/RUB"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(RICH_URI));
    }

    @Test
    void getRate_negativeDifferential_redirectedToBrokeURI() throws Exception {
        when(rateService.getRate(RUB)).thenReturn(-1.0);
        when(gifService.getGifURI(-1.0)).thenReturn(BROKE_URI);

        mockMvc.perform(get("/api/v1/result/RUB"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(BROKE_URI));
    }

    @Test
    void getRate_zeroDifferential_redirectedToZeroURI() throws Exception {
        when(rateService.getRate(RUB)).thenReturn(0.0);
        when(gifService.getGifURI(0.0)).thenReturn(ZERO_URI);

        mockMvc.perform(get("/api/v1/result/RUB"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ZERO_URI));
    }

    @Test
    void getRate_NaNDifferential_redirectedToFailURI() throws Exception {
        when(rateService.getRate(RUB)).thenReturn(Double.NaN);
        when(gifService.getGifURI(Double.NaN)).thenReturn(FAIL_URI);

        mockMvc.perform(get("/api/v1/result/RUB"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(FAIL_URI));
    }
}
