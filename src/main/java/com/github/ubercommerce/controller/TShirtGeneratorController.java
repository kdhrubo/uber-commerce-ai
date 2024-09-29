package com.github.ubercommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TShirtGeneratorController {

    private final ImageModel imageModel;


    @PostMapping("/generate")
    public String generate(TShirtGenerationRequest tShirtGenerationRequest, Model model){
        log.info("Generating image");
        ImageResponse response = imageModel.call(
                new ImagePrompt(tShirtGenerationRequest.description(),
                        OpenAiImageOptions.builder()
                                .withQuality("hd")
                                .withResponseFormat("url")
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024).build())

        );

        String url = response.getResult().getOutput().getUrl();

        log.info("url - {}", url);

        model.addAttribute("imageUrl", url);

        return "previewimg :: #previewImg";
    }


    @GetMapping({"", "/","/preview"})
    public String preview(Model model) {
        return "preview";
    }

}
