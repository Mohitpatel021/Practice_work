package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = " products")
public class Products {
   @Id
   private String id;
   private String name;
   private Integer quantity;

   @Column(name = "updated_at")
   @UpdateTimestamp
   private LocalDateTime updatedAt;

   public Products(String id, String name, Integer quantity, LocalDateTime updatedAt) {
      this.id = id;
      this.name = name;
      this.quantity = quantity;
      this.updatedAt = updatedAt;
   }

   public Products() {
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

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
   }

}
