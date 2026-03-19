package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductDocument;
import com.example.demo.model.Products;
import com.example.demo.repos.elastic.ProductERepo;
import com.example.demo.repos.jpa.ProductRepo;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    private ProductERepo productERepo;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/normal")
    public ResponseEntity<List<Products>>getProductFromNormalDb(@RequestParam(name="search_term")String searchTerm){
        return new ResponseEntity<>(productRepo.findByName(searchTerm),HttpStatus.OK);
    }
    @GetMapping("/elastic")
    public ResponseEntity<List<ProductDocument>>getProductFromElasaticIndex(@RequestParam(name="search_term")String search_terms){
        return new ResponseEntity<>(productERepo.findByNameFuzzy(search_terms),HttpStatus.OK);
    }
}
