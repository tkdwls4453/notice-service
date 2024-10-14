package com.example.noticeservice.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 재입고
 * 상품을 조회한다.
 * 상품 재고를 늘린다, -재입고 회차 증가 > 도메인 (product)
 * 재입고 알림 설정 유저 목록 읽어오기 -> 서비스
 * 유저에게 재입고 알림 메시지 순서대로 전송하기 -> 도메인 (notice)
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int stockQuantity;
    private int restockRound;

    public void restock(int quantity){
        this.stockQuantity += quantity;
        this.restockRound++;
    }

    public boolean isOutOfStock(){
        return this.stockQuantity <= 0;
    }
}
