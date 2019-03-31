package com.savaleks.service;

import com.savaleks.model.Product;

import java.util.List;

public interface ProductService {

    Product get(int productId);
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Product product);

    // business method
    List<Product> listActiveProducts();
    List<Product> listActiveProductsByCategory(int categoryId);
//    List<Product> getLatestActiveProducts(int count);
}
