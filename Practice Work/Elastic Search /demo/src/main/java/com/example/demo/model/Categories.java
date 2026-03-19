package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Categories {
    @Id
    private String id;
    private String name;
    private String description;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Categories() {
    }

    public Categories(String id, String name, String description, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    

}
