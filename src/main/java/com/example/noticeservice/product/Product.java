package com.example.noticeservice.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stockQuantity;
    private Integer restockRound;

    @Builder
    private Product(Long id, Integer stockQuantity) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.restockRound = 0;
    }

    public void restock(int quantity){
        this.stockQuantity += quantity;
        this.restockRound++;
    }

    public boolean isOutOfStock(){
        return this.stockQuantity <= 0;
    }

    public void decreaseStock(){
        this.stockQuantity--;
    }
}
