package com.savaleks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter @Setter @ToString
public class Address implements Serializable {

    @ToString.Exclude public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "address_line")
    @NotBlank(message = "Please enter address")
    private String addressLine;
    @NotBlank(message = "Please enter city")
    private String city;
    private String state;
    @NotBlank(message = "Please enter country")
    private String country;
    @NotBlank(message = "Please enter postcode")
    private String postcode;
    private boolean shipping;
    private boolean billing;

    @ManyToOne
    private User user;
}
