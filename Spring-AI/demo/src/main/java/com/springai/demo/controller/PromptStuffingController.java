package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api")
public class PromptStuffingController {

    private final ChatClient chatClient;

//    @Value("classpath:/promptTemplate/systemPromptTemplate.st")
    Resource userPromptTemplate;

    public PromptStuffingController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient =chatClient;
    }
}
