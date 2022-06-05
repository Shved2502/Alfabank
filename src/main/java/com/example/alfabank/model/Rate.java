package com.example.alfabank.model;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class Rate {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;
}
