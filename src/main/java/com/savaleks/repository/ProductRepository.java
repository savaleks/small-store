package com.savaleks.repository;

import com.savaleks.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    // JPQL query to fetch the all active products
    @Query("SELECT t FROM Product t WHERE t.active = :active")
    public List<Product> listActiveProducts();

    @Query("SELECT t FROM Product t WHERE t.active = 'true' AND categoryId = :categoryId")
    public List<Product> listActiveProductsByCategory(@Param("categoryId") int categoryId);

//    @Query(value = "SELECT u FROM Product WHERE active = 'true' ORDER BY id")
//    public List<Product> getLatestActiveProducts(@Param("id") int count);
}
