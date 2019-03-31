package com.savaleks.repository;

import com.savaleks.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT t FROM Category t WHERE t.active = 'true'")
    public List<Category> getAllByActive();
}
