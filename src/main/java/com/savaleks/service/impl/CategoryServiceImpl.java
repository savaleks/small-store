package com.savaleks.service.impl;

import com.savaleks.model.Category;
import com.savaleks.repository.CategoryRepository;
import com.savaleks.service.CategoryService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // creating some dummy data for testing purpose
//    private static List<Category> categories = new ArrayList<>();
//    static {
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Phones");
//        category.setDescription("This some description for phones");
//        category.setImageURL("pic1.png");
//        // active setter true by default
//
//        categories.add(category);
//
//        category = new Category();
//        category.setId(2);
//        category.setName("TV");
//        category.setDescription("This some description for television");
//        category.setImageURL("pic2.png");
//        // active setter true by default
//
//        categories.add(category);
//
//        category = new Category();
//        category.setId(3);
//        category.setName("Cameras");
//        category.setDescription("This some description for cameras");
//        category.setImageURL("pic3.png");
//        // active setter true by default
//
//        categories.add(category);
//    }

    @Override
    public List<Category> list() {
        return categoryRepository.getAllByActive();
    }

    @Override
    public Category get(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean add(Category category) {
        categoryRepository.save(category);
        return true;
    }
}
