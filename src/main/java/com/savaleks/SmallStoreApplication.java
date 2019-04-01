package com.savaleks;

import com.savaleks.model.Product;
import com.savaleks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class SmallStoreApplication implements CommandLineRunner {

    @Autowired
    ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SmallStoreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Sony L569");
        product1.setBrand("Sony");
        product1.setDescription("some text");
        product1.setQuantity(44);
        product1.setUnitPrice(BigDecimal.valueOf(12));
        product1.setCategoryId(1);
        product1.setSupplierId(1);

        productService.add(product1);
    }
}
