package com.pruebaback.prueba.product.application;

import com.pruebaback.prueba.product.domain.Product;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductInputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;

import java.util.List;

public interface ProductService {

    public ProductOutputDTO createProduct(ProductInputDTO productInputDTO);
    public Product findProductById(Long id);
    public ProductOutputDTO convertToProductDTO(Product product);
    public List<ProductOutputDTO> findProductsByAmount(Double amount);
}
