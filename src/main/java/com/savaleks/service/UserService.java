package com.savaleks.service;

import com.savaleks.model.Address;
import com.savaleks.model.Cart;
import com.savaleks.model.User;

import java.util.List;

public interface UserService {

    boolean addAddress(Address address);
    boolean addUser(User user);
    boolean addCart(Cart cart);
    User getByEmail(String email);
    Address getBillingAddress(User userId);
    List<Address> listShippingAddresses(User userId);
}
