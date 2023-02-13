package com.pruebaback.prueba.cart.domain;

import com.pruebaback.prueba.product.domain.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue
    private Long id;
    private Date date_order = new Date();
    private Date scheduled_delivery_date;
    private boolean has_discount ;
    private Double totalPrice;
    @ManyToMany
    @JoinTable(name = "cart_products",
    joinColumns = @JoinColumn(name = "cart_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getDate_order(){
        return this.date_order;
    }

    public void setDate_order(Date date_order){
        this.date_order= date_order;
    }

    public Date getScheduled_delivery_date(){
        return this.scheduled_delivery_date;
    }

    public void setScheduled_delivery_date(Date scheduled_delivery_date){
        this.scheduled_delivery_date = scheduled_delivery_date;
    }

    public boolean isHas_discount(){
        return this.has_discount;
    }

    public void setHas_discount(boolean has_discount){
        this.has_discount = has_discount;
    }


    public void setTotalPrice(Double totalPrice){
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        double amount = 0;
        for (Product product : this.products)
        {
            amount+= (product.getAmount());
        }

        if (isHas_discount()){
            amount = amount * 0.9;
        }

        return amount;
    }

    public Set<Product> getProducts(){
        return this.products;
    }

    public void setProducts(Set<Product> products){
        this.products = products;
    }

    public void addProduct(Product product) {

        if (product != null) {
            if (this.products == null) {
                this.products = new HashSet<>();
            }

            products.add(product);
        }
    }

}
