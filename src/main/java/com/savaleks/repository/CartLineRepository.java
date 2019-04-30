package com.savaleks.repository;

import com.savaleks.model.CartLine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartLineRepository extends CrudRepository<CartLine, Integer> {

    @Query("SELECT t FROM CartLine t WHERE t.cartId = :cartId")
    List<CartLine> list(int cartId);

    @Modifying
    @Query("SELECT t FROM CartLine t WHERE t.cartId = :cartId AND t.available = 1")
    List<CartLine> listAvailable(int cartId);
    @Modifying
    @Query("SELECT t FROM CartLine t WHERE t.cartId = :cartId AND t.product.id = :productId")
    CartLine getByCartAndProduct(int cartId, int productId);
}
