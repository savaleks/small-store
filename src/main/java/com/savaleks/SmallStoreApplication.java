package com.savaleks;

import com.savaleks.model.Category;
import com.savaleks.model.Product;
import com.savaleks.service.CategoryService;
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

    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(SmallStoreApplication.class, args);
    }

    // dummy data for testing
    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Sony L569");
        product1.setBrand("Sony");
        product1.setDescription("some text");
        product1.setQuantity(44);
        product1.setUnitPrice(BigDecimal.valueOf(12.33));
        product1.setCategoryId(1);
        product1.setSupplierId(1);
        product1.setActive(false);

        productService.add(product1);

        Category category1 = new Category();
        category1.setName("Phones");
        category1.setDescription("The best phones");
        category1.setImageURL("pic001.jpg");

        categoryService.add(category1);
    }
}
