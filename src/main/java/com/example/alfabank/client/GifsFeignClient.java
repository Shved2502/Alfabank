package com.example.alfabank.client;

import com.example.alfabank.model.Gif;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "gif", url = "${giphy.url}")
public interface GifsFeignClient {

    @GetMapping("/random")
    Gif getGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
