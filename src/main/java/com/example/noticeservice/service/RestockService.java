package com.example.noticeservice.service;

import com.example.noticeservice.domain.Product;
import com.example.noticeservice.repository.ProductRepository;
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
