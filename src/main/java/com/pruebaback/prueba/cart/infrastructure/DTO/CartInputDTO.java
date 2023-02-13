package com.pruebaback.prueba.cart.infrastructure.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CartInputDTO {

    private Date scheduled_delivery_date;
    private boolean has_discount ;
}
