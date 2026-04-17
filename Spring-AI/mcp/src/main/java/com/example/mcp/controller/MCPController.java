package com.example.mcp.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MCPController {

        private  final ChatClient chatClient;

        public MCPController(ChatClient.Builder chatClientBuilder,
                                   ToolCallbackProvider toolCallbackProvider) {
            this.chatClient = chatClientBuilder.defaultToolCallbacks(toolCallbackProvider)
                    .defaultAdvisors(new SimpleLoggerAdvisor())
                    .build();
        }

        @GetMapping("/chat")
        public String chat(@RequestHeader(value = "username",required = false) String username,
                           @RequestParam("message") String message) {
            return chatClient.prompt().user(message+ " My username is " + username)
                    .call().content();
        }
}