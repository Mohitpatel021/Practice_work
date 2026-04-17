package com.springai.demo.controller;


import com.springai.demo.config.TokenDetailsAdvisors;
import com.springai.demo.response.CountryList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StructuredResponse {

    private final ChatClient chatClient;

    private final TokenDetailsAdvisors tokenDetailsAdvisors;

    public StructuredResponse(@Qualifier("ollamaChatClient") ChatClient chatClient, TokenDetailsAdvisors tokenDetailsAdvisors) {
        this.chatClient = chatClient;
        this.tokenDetailsAdvisors = tokenDetailsAdvisors;
    }

    @GetMapping("/structured")
    public ResponseEntity<?> getStructureResponse(@RequestParam("message")String message){
        return new ResponseEntity<>(chatClient.prompt(message).system("""
                Give response ONLY in JSON format.
                
                {
                  "cities": [],
                  "country": string
                }
                Do not add any explanation.""").advisors(tokenDetailsAdvisors).call().entity(CountryList.class), HttpStatus.OK);
    }
}
