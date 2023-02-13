package com.pruebaback.prueba.product.infrastructure.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInputDTO {

    private String description;
    private Double amount;
}
