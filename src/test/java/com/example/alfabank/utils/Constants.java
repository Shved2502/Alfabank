package com.example.alfabank.utils;

import com.example.alfabank.model.Gif;
import com.example.alfabank.model.Rate;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    public static final String DISCLAIMER = "Disclaimer";
    public static final String LICENSE = "license";
    public static final String BASE = "USD";
    public static final int TIMESTAMP_TODAY = 1654430653;
    public static final int TIMESTAMP_YESTERDAY = 1654344253;
    public static final String RUB = "RUB";
    public static final String RRR = "RRR";
    public static final String BROKE_URI = "https://broke.com";
    public static final String RICH_URI = "https://rich.com";
    public static final String ZERO_URI = "https://zero.com";
    public static final String FAIL_URI = "https://fail.com";

    public static Rate getLowerRate() {
        Map<String, Double> ratesLower = new HashMap<>();
        ratesLower.put(RUB, 63.0);

        return Rate.builder()
                .disclaimer(DISCLAIMER)
                .license(LICENSE)
                .timestamp(TIMESTAMP_TODAY)
                .base(BASE)
                .rates(ratesLower)
                .build();
    }

    public static Rate getHigherRate() {
        Map<String, Double> ratesHigher = new HashMap<>();
        ratesHigher.put("RUB", 63.2);

        return Rate.builder()
                .disclaimer(DISCLAIMER)
                .license(LICENSE)
                .timestamp(TIMESTAMP_YESTERDAY)
                .base(BASE)
                .rates(ratesHigher)
                .build();
    }

    public static Gif getRichGif() {
        Map<String, Object> data = new HashMap<>();
        Map<String, String > meta = new HashMap<>();
        data.put("embed_url", RICH_URI);

        return Gif.builder()
                .data(data)
                .meta(meta)
                .build();
    }

    public static Gif getBrokeGif() {
        Map<String, Object> data = new HashMap<>();
        Map<String, String > meta = new HashMap<>();
        data.put("embed_url", BROKE_URI);

        return Gif.builder()
                .data(data)
                .meta(meta)
                .build();
    }

    public static Gif getZeroGif() {
        Map<String, Object> data = new HashMap<>();
        Map<String, String > meta = new HashMap<>();
        data.put("embed_url", ZERO_URI);

        return Gif.builder()
                .data(data)
                .meta(meta)
                .build();
    }

    public static Gif getFailGif() {
        Map<String, Object> data = new HashMap<>();
        Map<String, String > meta = new HashMap<>();
        data.put("embed_url", FAIL_URI);

        return Gif.builder()
                .data(data)
                .meta(meta)
                .build();
    }
}
