package com.pruebaback.prueba.cart.infrastructure.repository;

import com.pruebaback.prueba.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {

}
