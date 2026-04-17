package com.springai.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenDetailsAdvisors implements CallAdvisor {

    Logger logger = LoggerFactory.getLogger(TokenDetailsAdvisors.class);
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if(chatResponse.getMetadata()!=null){
            Usage usage = chatResponse.getMetadata().getUsage();
            if(usage.getCompletionTokens()!=null){
                logger.info("Usage token completion tokens: {} " , usage.toString());
            }
        }
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenDetailsAdvisors";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
