package com.springai.demo.controller;

import com.springai.demo.config.TokenDetailsAdvisors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api")
public class ChatMemmoryController {

    private final ChatClient chatClient;
    private final TokenDetailsAdvisors tokenDetailsAdvisors;

    public ChatMemmoryController(@Qualifier("chatmemeoryClient") ChatClient chatClient, TokenDetailsAdvisors tokenDetailsAdvisors) {
        this.chatClient =chatClient;
        this.tokenDetailsAdvisors = tokenDetailsAdvisors;
    }

    @GetMapping("/memory")
    public String chat(@RequestParam("message") String message,@RequestParam("username")String username){
        return this.chatClient
                .prompt(message)
                .advisors(tokenDetailsAdvisors)
                .advisors(a->a.param(CONVERSATION_ID,username))
                .call().content();
    }
}
