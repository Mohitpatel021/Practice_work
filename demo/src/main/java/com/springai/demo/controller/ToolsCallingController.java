package com.springai.demo.controller;

import com.springai.demo.tools.TimeToolsCalling;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/tool")
public class ToolsCallingController {

    private final ChatClient chatClient;
    private final TimeToolsCalling toolsCalling;
    public ToolsCallingController(@Qualifier("timeChatClient") ChatClient chatClient, TimeToolsCalling toolsCalling) {
        this.chatClient = chatClient;
        this.toolsCalling = toolsCalling;
    }


    @GetMapping("/local-time")
    public ResponseEntity<String> localTime(@RequestParam("username") String username,
                                              @RequestParam("message") String message) {
        String answer = chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .user(message)
                .call().content();
        return ResponseEntity.ok(answer);
    }
}


