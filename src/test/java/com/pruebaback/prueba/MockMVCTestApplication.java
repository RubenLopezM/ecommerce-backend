package com.pruebaback.prueba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebaback.prueba.cart.application.CartService;
import com.pruebaback.prueba.cart.domain.Cart;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartInputDTO;
import com.pruebaback.prueba.cart.infrastructure.DTO.CartOutputDTO;
import com.pruebaback.prueba.cart.infrastructure.controller.CartController;
import com.pruebaback.prueba.cart.infrastructure.repository.CartRepo;
import com.pruebaback.prueba.product.domain.Product;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductInputDTO;
import com.pruebaback.prueba.product.infrastructure.DTO.ProductOutputDTO;
import com.pruebaback.prueba.product.infrastructure.repository.ProductRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MockMVCTestApplication {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartService cartService;

    private CartInputDTO cartInputDTO;
    private Cart cart;

    private ProductInputDTO productInputDTO;
    private Product product;

    @BeforeAll
    public void starting(){
        cartInputDTO= new CartInputDTO();
        cartInputDTO.setHas_discount(true);
        cartInputDTO.setScheduled_delivery_date(new Date());

        cart = new Cart();
        cart.setHas_discount(true);
        cart.setDate_order(new Date());
        cartRepo.save(cart);

        productInputDTO= new ProductInputDTO();
        productInputDTO.setAmount(50.2);
        productInputDTO.setDescription("A book");

        product = new Product();
        product.setAmount(50.2);
        product.setDescription("A book");
        productRepo.save(product);
        cart.addProduct(product);

    }

    @Test
    @DisplayName("Test the method to create a cart")
    public void test_create_cart() throws Exception {

        MvcResult resultado = this.mockMvc.perform(post("/api/v0/carts").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartInputDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String contenido = resultado.getResponse().getContentAsString();

        CartOutputDTO cartOutputDTO = new ObjectMapper()
                .readValue(contenido, new TypeReference<CartOutputDTO>() {
                });

        checkCart(cartOutputDTO);

    }

    @Test
    @DisplayName("Test the method to create a product")
    public void test_create_product() throws Exception {

        MvcResult resultado = this.mockMvc.perform(post("/api/v0/products").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productInputDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String contenido = resultado.getResponse().getContentAsString();

        ProductOutputDTO productOutputDTO = new ObjectMapper()
                .readValue(contenido, new TypeReference<ProductOutputDTO>() {
                });

        checkProducto(productOutputDTO);

    }

    @Test
    @DisplayName("Calling method to get cart by id")
    void test_get_cart() throws Exception {
        MvcResult resultado  = this.mockMvc.perform(get("/api/v0/carts/1")).andExpect(status().isOk())
                .andReturn();
        String contenido= resultado.getResponse().getContentAsString();

        CartOutputDTO cartOutputDTO= new ObjectMapper().readValue(contenido, new TypeReference<CartOutputDTO>() {   }); // Use TypeReference to map the List.

        Cart cart = cartService.findCartbyId(1l);

        Assertions.assertEquals(cartOutputDTO.isHas_discount(), cart.isHas_discount());
        Assertions.assertEquals(cartOutputDTO.getScheduled_delivery_date(), cart.getScheduled_delivery_date());

    }

    @Test
    @DisplayName("Testing the method to add a producto to the cart")
    void test_addProduct_toCart() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v0/carts/{cart_id}/product/{product_id}",1,1))
                .andExpect(status().isOk());


    }



    @Test
    @DisplayName("Testing the delete method")
    void test_delete_Cart() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v0/carts/{id}",1))
                .andExpect(status().isOk());


    }

    private void checkCart(CartOutputDTO cartOutputDTO){
        Assertions.assertEquals(cartOutputDTO.getScheduled_delivery_date(),cartInputDTO.getScheduled_delivery_date());
        Assertions.assertEquals(cartOutputDTO.isHas_discount(),cartInputDTO.isHas_discount());


    }

    private void checkProducto(ProductOutputDTO productOutputDTO){
        Assertions.assertEquals(productOutputDTO.getAmount(),productInputDTO.getAmount());
        Assertions.assertEquals(productOutputDTO.getDescription(),productInputDTO.getDescription());


    }
}
