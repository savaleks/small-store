package com.savaleks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @ToString
public class Cart implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "grand_total")
    private double grandTotal;
    @Column(name = "cart_lines")
    private int cartLines;

    @OneToOne
    private User user;
}
