package com.github.ubercommerce.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TShirtGeneratorApiController {

    private final ImageModel imageModel;

    private final String prompt = """
           A vibrant, abstract art piece featuring swirling colors and bold brushstrokes, inspired by the concept of energy and movement, for a modern and edgy t-shirt design.""";


    @PostMapping("/generate")
    public TShirtGenerationResponse generate(@RequestBody TShirtGenerationRequest tShirtGenerationRequest){

        String finalPrompt = prompt;


        log.info("Final prompt - {}", finalPrompt);


        ImageResponse response = imageModel.call(
                new ImagePrompt(finalPrompt,
                        OpenAiImageOptions.builder()
                                .withQuality("hd")
                                .withResponseFormat("url")
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024).build())

        );

        String url = response.getResult().getOutput().getUrl();

        log.info("url - {}", url);

        return new TShirtGenerationResponse(url);
    }
}
