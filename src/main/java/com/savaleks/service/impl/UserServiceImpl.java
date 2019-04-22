package com.savaleks.service.impl;

import com.savaleks.model.Address;
import com.savaleks.model.Cart;
import com.savaleks.model.User;
import com.savaleks.repository.AddressRepository;
import com.savaleks.repository.CartRepository;
import com.savaleks.repository.UserRepository;
import com.savaleks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean addAddress(Address address) {
        addressRepository.save(address);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean addCart(Cart cart) {
        cartRepository.save(cart);
        return true;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Address getBillingAddress(User userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public List<Address> listShippingAddresses(User userId) {
        return addressRepository.listShippingAddresses(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
