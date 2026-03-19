package com.example.demo.repos.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductDocument;

@Repository
public interface ProductERepo extends ElasticsearchRepository<ProductDocument,String>{
    @Query("{\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"1\"}}}")
    public List<ProductDocument> findByNameFuzzy(String searchTerm);
   
}
