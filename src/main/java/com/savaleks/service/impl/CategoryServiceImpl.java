package com.savaleks.service.impl;

import com.savaleks.model.Category;
import com.savaleks.repository.CategoryRepository;
import com.savaleks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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
