package com.pruebaback.prueba.cart.infrastructure.DTO;

import com.pruebaback.prueba.product.domain.Product;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartOutputDTO {

    private Long id;
    private Date scheduled_delivery_date;
    private boolean has_discount ;
    private Double totalPrice;
    private Set<ProductOutputDTO> products;
}
