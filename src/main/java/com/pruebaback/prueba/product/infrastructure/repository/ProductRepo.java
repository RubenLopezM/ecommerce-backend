package com.pruebaback.prueba.product.infrastructure.repository;

import com.pruebaback.prueba.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByAmountLessThanEqual(Double amount);
}
