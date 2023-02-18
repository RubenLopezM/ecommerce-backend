package com.pruebaback.prueba.cart.application;

import com.pruebaback.prueba.cart.domain.Cart;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartInputDTO;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartOutputDTO;
import com.pruebaback.prueba.product.domain.Product;

import java.util.Date;
import java.util.List;

public interface CartService {

    public void addProductToCart(Long id_product, Long id_cart);
    public Cart findCartbyId(Long id);
    public CartOutputDTO createCart(CartInputDTO cartInputDTO);
    public CartOutputDTO getCartDetails(Long id);
    public void deleteCard(Long id);
    public void deleteCardByDate();

}
