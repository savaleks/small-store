package com.savaleks.service;

import com.savaleks.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();
    Category get(int id);
}
