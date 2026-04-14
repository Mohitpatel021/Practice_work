package com.springai.demo.config;

import com.springai.demo.rag.PIIPostProcessor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(20).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean
    public ChatClient ollamaChatClient(ChatModel chatModel,ChatMemory chatMemory) {
        ChatOptions chatOption=ChatOptions.builder()
                .model("gpt-4.1-mini").maxTokens(100).temperature(0.2)
                .build();
        MessageChatMemoryAdvisor build = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(chatModel)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),build))
                .defaultOptions(chatOption)
                .defaultSystem("""
                        You are an internal HR assistant. Your role is to help
                                               employees with questions related to HR policies, such as
                                               leave policies, working hours, benefits, and code of conduct.
                                               If a user asks for help with anything outside of these topics,
                                               kindly inform them that you can only assist with queries related to
                                               HR policies.""")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean(name="chatmemeoryClient")
    public ChatClient chatmemeoryClient(ChatModel chatModel, ChatMemory chatMemory, RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {
        ChatOptions chatOption=ChatOptions.builder()
                .model("gpt-4.1-mini").maxTokens(100).temperature(0.2)
                .build();
        MessageChatMemoryAdvisor build = MessageChatMemoryAdvisor.builder(chatMemory).build();
        TokenDetailsAdvisors tokenDetailsAdvisors = new TokenDetailsAdvisors();
        return ChatClient.builder(chatModel)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),build,tokenDetailsAdvisors,retrievalAugmentationAdvisor))
                .defaultOptions(chatOption)
                .build();
    }

    @Bean
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore,ChatClient.Builder chatClientBuilder) {
            return RetrievalAugmentationAdvisor.builder()
                    .queryTransformers(TranslationQueryTransformer.builder()
                            .chatClientBuilder(chatClientBuilder)
                            .targetLanguage("english")
                            .build())
                    .documentRetriever(VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).topK(3).similarityThreshold(0.5).build())
                    .documentPostProcessors(PIIPostProcessor.builder())
                    .build();
    }
}
