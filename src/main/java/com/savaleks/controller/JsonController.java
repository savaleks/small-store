package com.savaleks.controller;

import com.savaleks.model.Product;
import com.savaleks.repository.ProductRepository;
import com.savaleks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json/data")
public class JsonController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){
        return productRepository.listActiveProducts();
    }

    @GetMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllAdminProducts(){
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getAllProductsByCategory(@PathVariable("id") int id){

        return productRepository.listActiveProductsByCategory(id);
    }
}
