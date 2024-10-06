package com.github.ubercommerce.configuration;

import org.springframework.ai.image.ImageModel;

import org.springframework.ai.stabilityai.StabilityAiImageModel;
import org.springframework.ai.stabilityai.api.StabilityAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StabilityAIConfiguration {




    @Bean
    ImageModel imageModel(@Value("${SPRINGAI_STABILITYAI_KEY}") String apiKey) {
        return new StabilityAiImageModel(new StabilityAiApi(apiKey));
    }


}
