package com.example.demo.repos.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, String> {
    @Query("SELECT p FROM Products p WHERE p.name LIKE %?1")
    public List<Products> findByName(String name);
}
