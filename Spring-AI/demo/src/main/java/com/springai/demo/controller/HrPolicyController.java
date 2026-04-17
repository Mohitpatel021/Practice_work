package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/hr")
public class HrPolicyController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplate/HRSystemTemplate.st")
    Resource hrPromptTemplate;

    public HrPolicyController(@Qualifier("chatmemeoryClient") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }
    @GetMapping("/q/chat")
    public String hrPolicy(@RequestParam("message") String message, @RequestParam("username")String username){
//        SearchRequest build = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
//        List<Document> documents = vectorStore.similaritySearch(build);
//        System.out.println(":User message: "+message);
//        System.out.println("===== RETRIEVED DOCS =====");
//        documents.forEach(doc -> System.out.println(doc.getText()));
//        System.out.println("===== END =====");
//        String data= documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        return chatClient.prompt()
//                .system(spec -> spec.text(hrPromptTemplate)
//                .param("documents", data).param("input",message))
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .user(message)
                .call().content();
    }
}
