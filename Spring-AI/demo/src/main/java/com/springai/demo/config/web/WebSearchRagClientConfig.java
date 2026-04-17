package com.springai.demo.config.web;

import com.springai.demo.config.TokenDetailsAdvisors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class WebSearchRagClientConfig {


    @Bean(name="webSearchRagChatClient")
    public ChatClient webSearchRagChatClient(ChatModel chatModel, ChatMemory chatMemory, RestClient.Builder restClient) {
        ChatOptions chatOption=ChatOptions.builder()
                .model("gpt-4.1-mini").maxTokens(100).temperature(0.2)
                .build();
        MessageChatMemoryAdvisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        TokenDetailsAdvisors tokenDetailsAdvisors = new TokenDetailsAdvisors();
        var webSearchRagAdvisor=RetrievalAugmentationAdvisor
                .builder()
                .documentRetriever(WebSearchRetriveDriver.builder().restClient(restClient).maxResults(5).build()).build();
        return ChatClient.builder(chatModel)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),memoryAdvisor,tokenDetailsAdvisors,webSearchRagAdvisor))
                .defaultOptions(chatOption)
                .build();
    }
}
