package com.savaleks.repository;

import com.savaleks.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    // Queries to fetch the all active products
    @Query("SELECT t FROM Product t WHERE t.active = 0")
    public List<Product> listActiveProducts();

    @Modifying
    @Query("SELECT t FROM Product t WHERE t.active = 0 AND t.categoryId = :id")
    public List<Product> listActiveProductsByCategory(int id);

//    @Query(value = "SELECT u FROM Product WHERE active = 0 ORDER BY id")
//    public List<Product> getLatestActiveProducts(@Param("id") int count);

}
