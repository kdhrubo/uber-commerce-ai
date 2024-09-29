package com.github.ubercommerce.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }


    @Bean
    ImageModel imageModel(@Value("${SPRINGAI_OPENAI_KEY}") String apiKey) {
        return new OpenAiImageModel(new OpenAiImageApi(apiKey));
    }


    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }
}
