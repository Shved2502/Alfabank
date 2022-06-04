package com.example.alfabank.model;

import lombok.Data;

import java.util.Map;

@Data
public class Rate {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;
}
