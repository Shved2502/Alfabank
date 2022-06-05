package com.example.alfabank.service.impl;

import com.example.alfabank.client.GifsFeignClient;
import com.example.alfabank.service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

    private final GifsFeignClient gifsFeignClient;
    @Value("${giphy.key}")
    private String apiKey;
    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.zero}")
    private String zeroTag;
    @Value("${giphy.fail}")
    private String failTag;

    @Override
    public String getGifURI(Double diff) {
        String tag;
        if (diff < 0) {
            tag = brokeTag;
        } else if (diff > 0) {
            tag = richTag;
        } else if (diff == 0) {
            tag = zeroTag;
        } else {
            tag = failTag;
        }
        return (String) gifsFeignClient.getGif(apiKey, tag).getData().get("embed_url");
    }
}
