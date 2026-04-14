package com.springai.demo.config.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.net.HttpHeaders;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


public class WebSearchRetriveDriver implements DocumentRetriever {

    private static final String TAVILY_API_KEY="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";
    private static final String TAVILY_API_URL="https://api.tavily.com/search";
    private static final int DEFAULT_RESULT_LIMIT=5;
    private final int resultLimit;
    private final RestClient restClient;


    public WebSearchRetriveDriver(RestClient.Builder restClient, int resultLimit) {
        Assert.notNull(restClient, "restClient must not be null");
        String apikey=System.getenv("TAVILY_API_KEY");
        Assert.hasText(apikey, "TAVILY_API_KEY must be set");
        this.restClient=restClient.baseUrl(TAVILY_API_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"Bearer "+apikey)
                .build();
        if(resultLimit<0){
            throw new IllegalArgumentException("resultLimit must be greater than 0");
        }
        this.resultLimit=resultLimit;
    }


    @Override
    public List<Document> retrieve(Query query) {
        Assert.notNull(query, "query must not be null");
        String text=query.text();
        Assert.hasText(text, "text must not be empty");
        TavilyResponsePayload response=restClient.post()
                .body(new TavilyRequestPayload(text,"advanced",resultLimit))
                .retrieve()
                .body(TavilyResponsePayload.class);
        if(response==null || CollectionUtils.isEmpty(response.results())){
                return List.of();
        }
        List<Document> docs=new ArrayList<>(response.results().size());
        for (TavilyResponsePayload.Hit hit:response.results()){
            Document doc=Document.builder()
                    .text(hit.content())
                    .metadata("title",hit.title())
                    .metadata("url",hit.url())
                    .score(hit.score()).build();
            docs.add(doc);
        }
        return docs;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record TavilyRequestPayload(String query, String searchDepth, int maxResults){}

    record TavilyResponsePayload(List<Hit> results){
        record Hit(String title,String url ,String content,Double score){}
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private RestClient.Builder restClient;
        private int resultLimit=DEFAULT_RESULT_LIMIT;

        public Builder restClient(RestClient.Builder clientBuilder){
            this.restClient=clientBuilder;
            return this;
        }
        public Builder maxResults(int maxResult){
            Assert.isTrue(maxResult>0,"maxResults must be greater than 0");
            this.resultLimit=maxResult;
            return this;
        }
        public WebSearchRetriveDriver  build(){
            return new WebSearchRetriveDriver(restClient,resultLimit);
        }
    }
}
