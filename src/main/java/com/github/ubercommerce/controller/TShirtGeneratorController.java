package com.github.ubercommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;

import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
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
                        StabilityAiImageOptions.builder()
                                .withStylePreset("cinematic")
                                .withN(1)
                                .withHeight(512)
                                .withWidth(512).build())

        );

        // log.info("Response: {}", response.getResult());

        log.info("Response: {}", response.getResult().getMetadata());
        log.info("Image: {}", response.getResult().getOutput());

        model.addAttribute("image", response.getResult().getOutput().getB64Json());

        return "previewimg :: #previewImg";
    }


    @GetMapping({"", "/","/preview"})
    public String preview(Model model) {
        return "preview";
    }

}
