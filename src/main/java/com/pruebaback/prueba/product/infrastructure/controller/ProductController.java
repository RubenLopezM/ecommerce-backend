package com.pruebaback.prueba.product.infrastructure.controller;

import com.pruebaback.prueba.product.application.ProductService;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductInputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/products")
    public ResponseEntity<ProductOutputDTO> createProduct(@RequestBody ProductInputDTO productInputDTO){
        return new ResponseEntity<>(productService.createProduct(productInputDTO), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductOutputDTO>> getProductsbyAmount(@RequestParam Double amount){
        return new ResponseEntity<>(productService.findProductsByAmount(amount), HttpStatus.OK);
    }

}
