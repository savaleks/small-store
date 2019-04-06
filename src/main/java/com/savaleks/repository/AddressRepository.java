package com.savaleks.repository;

import com.savaleks.model.Address;
import com.savaleks.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query("SELECT t FROM Address t WHERE t.user = :userId AND t.billing = 1")
    Address findByUserId(User userId);

    @Query("SELECT t FROM Address t WHERE t.user = :userId AND t.shipping = 1")
    List<Address> listShippingAddresses(User userId);
}
