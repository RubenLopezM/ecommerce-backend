package com.pruebaback.prueba.product.infrastructure.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductOutputDTO {

    private Long id;
    private String description;
    private Double amount;
}
