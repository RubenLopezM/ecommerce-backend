package com.pruebaback.prueba.product.domain;

import com.pruebaback.prueba.cart.domain.Cart;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double amount;
    @ManyToMany
    Set<Cart> carts = new HashSet<>();

}
