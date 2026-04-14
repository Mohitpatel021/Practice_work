package com.springai.demo.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HRPolicyDataLoader {

    private final VectorStore  vectorStore;

    @Value("classpath:Hr_policy.pdf")
    Resource promptTemplate;

    public HRPolicyDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void hrPolicyDataLoader(){
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(promptTemplate);
        List<Document> docs=tikaDocumentReader.get();
        TextSplitter textSplitter= TokenTextSplitter.builder().withChunkSize(100).withMaxNumChunks(400).build();
        vectorStore.add(textSplitter.split(docs));
    }
}
