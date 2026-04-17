package com.springai.demo.controller;

import com.springai.demo.config.TokenDetailsAdvisors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamController {

    private final ChatClient chatClient;
    private final TokenDetailsAdvisors tokenDetailsAdvisors;

    public StreamController(@Qualifier("ollamaChatClient") ChatClient chatClient, TokenDetailsAdvisors tokenDetailsAdvisors) {
        this.chatClient =chatClient;
        this.tokenDetailsAdvisors = tokenDetailsAdvisors;
    }

    @GetMapping("/stream")
    public Flux<String> chat(@RequestParam("message") String message){
        return this.chatClient
                .prompt(message)
                .advisors(tokenDetailsAdvisors)
                .system("""
                      You are an internal HR assistant. Your role is to help\\s
                                             employees with questions related to HR policies, such as\\s
                                             leave policies, working hours, benefits, and code of conduct.
                                             If a user asks for help with anything outside of these topics,\\s
                                             kindly inform them that you can only assist with queries related to\\s
                                             HR policies.""")
                .stream().content();
    }
}
