package com.savaleks.service.impl;

import com.savaleks.model.Category;
import com.savaleks.service.CategoryService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    // creating some dummy data for testing purpose
    private static List<Category> categories = new ArrayList<>();
    static {
        Category category = new Category();
        category.setId(1);
        category.setName("Phones");
        category.setDescription("This some description for phones");
        category.setImageURL("pic1.png");
        // active setter true by default

        categories.add(category);

        category = new Category();
        category.setId(2);
        category.setName("TV");
        category.setDescription("This some description for television");
        category.setImageURL("pic2.png");
        // active setter true by default

        categories.add(category);

        category = new Category();
        category.setId(3);
        category.setName("Cameras");
        category.setDescription("This some description for cameras");
        category.setImageURL("pic3.png");
        // active setter true by default

        categories.add(category);
    }

    @Override
    public List<Category> list() {
        return categories;
    }

    @Override
    public Category get(int id) {
        for (Category category : categories){
            if (category.getId() == id);
            return category;
        }
        return null;
    }
}
