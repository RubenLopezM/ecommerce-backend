package com.pruebaback.prueba.product.application;

import com.pruebaback.prueba.cart.domain.Cart;
import com.pruebaback.prueba.exceptions.CartNotFoundException;
import com.pruebaback.prueba.product.domain.Product;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductInputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;
import com.pruebaback.prueba.product.infrastructure.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public ProductOutputDTO createProduct(ProductInputDTO productInputDTO) {
        Product product = this.convertToProduct(productInputDTO);
        productRepo.save(product);
        return this.convertToProductDTO(product);
    }

    @Override
    public Product findProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(()-> new CartNotFoundException("This cart was not found"));
        return product;
    }

    private Product convertToProduct(ProductInputDTO productInputDTO){
        Product product = new Product();
        product.setAmount(productInputDTO.getAmount());
        product.setDescription(productInputDTO.getDescription());
        return product;
    }

    public ProductOutputDTO convertToProductDTO(Product product){

        ProductOutputDTO productOutputDTO = new ProductOutputDTO();
        productOutputDTO.setId(product.getId());
        productOutputDTO.setAmount(product.getAmount());
        productOutputDTO.setDescription(product.getDescription());
        return productOutputDTO;
    }

    @Override
    public List<ProductOutputDTO> findProductsByAmount(Double amount) {
        List<Product> products = productRepo.findByAmountLessThanEqual(amount);
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }
}
