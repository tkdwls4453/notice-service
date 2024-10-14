package com.example.noticeservice.product.service;

import com.example.noticeservice.notification.service.NoticeService;
import com.example.noticeservice.product.Product;
import com.example.noticeservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestockService {

    private final ProductRepository productRepository;
    private final NoticeService noticeService;

    public void restock(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new IllegalArgumentException()
        );

        product.restock(quantity);
        noticeService.createAndSend(product);

    }
}
