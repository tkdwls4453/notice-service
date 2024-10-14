package com.example.noticeservice.product.repository;

import com.example.noticeservice.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
