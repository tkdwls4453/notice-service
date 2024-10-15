package com.example.noticeservice.product.controller;

import com.example.noticeservice.product.service.RestockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final RestockService restockService;
    private final int RESTOCK_QUANTITY = 100;

    @PostMapping("/products/{productId}/notifications/re-stock")
    public ResponseEntity<Void> restock(
        @PathVariable(name = "productId") Long productId
    ) {
        restockService.restock(productId, RESTOCK_QUANTITY);
        return ResponseEntity.ok(null);
    }

}
