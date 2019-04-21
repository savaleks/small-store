package com.savaleks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter @Setter @ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @NotBlank(message = "Please enter the Product Name")
    private String name;
    @NotBlank(message = "Please enter the Brand Name")
    private String brand;
    @JsonIgnore
    @NotBlank(message = "Please enter the description")
    private String description;
    @Column(name = "unit_price")
    @Min(value = 1, message = "The price cannot be less than 1")
    private BigDecimal unitPrice;
    private int quantity;
    @Column(name = "is_active")
    private boolean active = true;
    @Column(name = "category_id")
    @JsonIgnore
    private int categoryId;
    @Column(name = "supplier_id")
    @JsonIgnore
    private int supplierId;
    private int purchases;
    private int views;

    @Transient
    private MultipartFile file;

    // default constructor
    public Product (){
        this.code = "PROD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }
}
