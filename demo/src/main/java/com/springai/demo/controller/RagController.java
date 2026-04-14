package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/rag")
public class RagController
{
    private final ChatClient chatClient;
    private final ChatClient webSearchClient;
    private final VectorStore vectorStore;
    @Value("classpath:/promptTemplate/systemPromptRandomDataTempalte.st")
    Resource promptTemplate;

    public RagController(@Qualifier("webSearchRagChatClient") ChatClient webSearchClient, @Qualifier("chatmemeoryClient") ChatClient chatClient, VectorStore vectorStore)
    {
        this.chatClient=chatClient;
        this.webSearchClient=webSearchClient;
        this.vectorStore=vectorStore;
    }

    @GetMapping("/random/chat")
    public String randomChat(@RequestParam("message") String message, @RequestParam("username")String username){
        SearchRequest build = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
        System.out.println("build search Request is here : "+ build.toString());
        List<Document> documents = vectorStore.similaritySearch(build);
        System.out.println("List of DOCUMENT over here : ");
        assert documents != null;
        documents.forEach(System.out::println);
        String data= documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        System.out.println("data over here : "+data);
        return chatClient.prompt()
                .system(spec -> spec.text(promptTemplate)
                        .param("documents", data))
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .user(message)
                .call().content();
    }


}
