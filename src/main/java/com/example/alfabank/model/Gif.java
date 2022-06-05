package com.example.alfabank.model;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class Gif {
    private Map<String, Object> data;
    private Map<String, String> meta;
}
