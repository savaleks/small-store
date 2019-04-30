package com.savaleks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @ToString
public class CartLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cart_id")
    private int cartId;
    private double total;
    @Column(name = "product_count")
    private int productCount;
    @Column(name = "buying_price")
    private double buyingPrice;
    @Column(name = "is_available")
    private boolean available = true;

    @OneToOne
    private Product product;
}
