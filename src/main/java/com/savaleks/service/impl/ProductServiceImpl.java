package com.savaleks.service.impl;

import com.savaleks.model.Product;
import com.savaleks.repository.ProductRepository;
import com.savaleks.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product get(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public boolean add(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean update(Product product) {
        Product newProduct = productRepository.findById(product.getId()).orElse(null);
        if (newProduct != null){
            productRepository.save(product);
        }
        return true;
    }

    @Override
    public boolean delete(Product product) {
        product.setActive(false);
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> listActiveProducts() {
        return productRepository.listActiveProducts();
    }

    @Override
    public List<Product> listActiveProductsByCategory(int categoryId) {
        return productRepository.listActiveProductsByCategory(categoryId);
    }

//    @Override
//    public List<Product> getLatestActiveProducts(int count) {
//
//        return productRepository.getLatestActiveProducts(count);
//    }
}
