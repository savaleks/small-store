package com.savaleks.service.impl;

import com.savaleks.model.Cart;
import com.savaleks.model.CartLine;
import com.savaleks.repository.CartLineRepository;
import com.savaleks.repository.CartRepository;
import com.savaleks.service.CartLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CartLineServiceImpl implements CartLineService {

    @Autowired
    private CartLineRepository cartLineRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartLine get(int id) {
        return cartLineRepository.findById(id).orElse(null);
    }

    @Override
    public boolean add(CartLine cartLine) {
        cartLineRepository.save(cartLine);
        return true;
    }

    @Override
    public boolean update(CartLine cartLine) {
        CartLine newCart = cartLineRepository.findById(cartLine.getId()).orElse(null);
        if (newCart != null){
            cartLineRepository.save(cartLine);
        }
        return true;
    }

    @Override
    public boolean delete(CartLine cartLine) {
        cartLineRepository.delete(cartLine);
        return true;
    }

    @Override
    public List<CartLine> list(int cartId) {
        return cartLineRepository.list(cartId);
    }

    @Override
    public List<CartLine> listAvailable(int cartId) {
        return cartLineRepository.listAvailable(cartId);
    }

    @Override
    public CartLine getByCartAndProduct(int cartId, int productId) {
        return cartLineRepository.getByCartAndProduct(cartId, productId);
    }

    @Override
    public boolean updateCart(Cart cart) {
        if (cart != null){
            cartRepository.save(cart);
            return true;
        }
        return false;
    }
}
