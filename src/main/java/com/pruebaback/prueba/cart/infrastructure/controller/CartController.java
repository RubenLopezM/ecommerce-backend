package com.pruebaback.prueba.cart.infrastructure.controller;

import com.pruebaback.prueba.cart.application.CartService;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartInputDTO;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartOutputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductInputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/carts")
    public ResponseEntity<CartOutputDTO> createCart(@RequestBody CartInputDTO cartInputDTO){
        return new ResponseEntity<>(cartService.createCart(cartInputDTO), HttpStatus.CREATED);
    }

    @GetMapping("/carts/{cart_id}")
    public ResponseEntity<CartOutputDTO> getCart(@PathVariable Long cart_id){
        return new ResponseEntity<>(cartService.getCartDetails(cart_id), HttpStatus.OK);
    }

    @DeleteMapping("/carts/{cart_id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cart_id){
        cartService.deleteCard(cart_id);
        return new ResponseEntity<>("The cart with id "+ cart_id + " has been deleted", HttpStatus.OK);
    }

    @GetMapping("carts/{cart_id}/product/{product_id}")
    public ResponseEntity<CartOutputDTO> addProductToCart(@PathVariable Long cart_id, @PathVariable Long product_id){
        cartService.addProductToCart(product_id, cart_id);
        return new ResponseEntity<>(cartService.getCartDetails(cart_id), HttpStatus.OK);
    }
}
