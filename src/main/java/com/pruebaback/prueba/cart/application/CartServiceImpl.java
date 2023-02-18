package com.pruebaback.prueba.cart.application;

import com.pruebaback.prueba.cart.domain.Cart;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartInputDTO;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartOutputDTO;
import com.pruebaback.prueba.cart.infrastructure.repository.CartRepo;
import com.pruebaback.prueba.exceptions.CartNotFoundException;
import com.pruebaback.prueba.product.application.ProductService;
import com.pruebaback.prueba.product.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductService productService;

    @Override
    public void addProductToCart(Long product_id, Long id_cart) {
        Cart cart = this.findCartbyId(id_cart);
        Product product = productService.findProductById(product_id);
        cart.addProduct(product);
        Double price = cart.getTotalPrice();
        cart.setTotalPrice(price);
        cartRepo.save(cart);

    }

    @Override
    @Cacheable(value = "cartsCache")
    public Cart findCartbyId(Long id) {
        Cart cart = cartRepo.findById(id).orElseThrow(()-> new CartNotFoundException("This cart was not found"));
        return cart;
    }

    @Override
    public CartOutputDTO createCart(CartInputDTO cartInputDTO) {
        Cart cart = this.convertToCart(cartInputDTO);
        cartRepo.save(cart);
        return this.convertToCartDTO(cart);
    }

    @Override
    public CartOutputDTO getCartDetails(Long id) {
        Cart cart = this.findCartbyId(id);
        return this.convertToCartDTO(cart);
    }

    @Override
    public void deleteCard(Long id) {
        Cart cart = this.findCartbyId(id);
        cartRepo.delete(cart);
    }



    @Override
    @Scheduled(fixedRate = 10000)
    public void deleteCardByDate() {

        List<Cart>  carts = cartRepo.findAll();
        if (carts.size()>0) {
            for (Cart cart: carts){
                if (this.checkCartDate(cart)){
                    cartRepo.delete(cart);
                }
            }
        }
    }

    private boolean checkCartDate(Cart cart){
        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        return  (((timestamp.getTime() - cart.getDate_order().getTime())/1000)/60>10);
    }


    private Cart convertToCart(CartInputDTO cartInputDTO){
        Cart cart = new Cart();
        cart.setDate_order(new Date());
        cart.setScheduled_delivery_date(cartInputDTO.getScheduled_delivery_date());
        cart.setHas_discount(cartInputDTO.isHas_discount());
        return cart;
    }

    public CartOutputDTO convertToCartDTO(Cart cart){

        CartOutputDTO cartOutputDTO = new CartOutputDTO();
        cartOutputDTO.setHas_discount(cart.isHas_discount());
        cartOutputDTO.setId(cart.getId());
        cartOutputDTO.setScheduled_delivery_date(cart.getScheduled_delivery_date());
        cartOutputDTO.setTotalPrice(cart.getTotalPrice());
        cartOutputDTO.setProducts(cart.getProducts().
                stream().
                map((product)-> productService.convertToProductDTO(product)).collect(Collectors.toSet()));
        return cartOutputDTO;
    }
}
