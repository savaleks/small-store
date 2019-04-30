package com.savaleks.service;

import com.savaleks.model.Cart;
import com.savaleks.model.CartLine;

import java.util.List;

public interface CartLineService {

    public CartLine get(int id);
    public boolean add(CartLine cartLine);
    public boolean update(CartLine cartLine);
    public boolean delete(CartLine cartLine);
    public List<CartLine> list(int cartId);

    public List<CartLine> listAvailable(int cartId);
    public CartLine getByCartAndProduct(int cartId, int productId);
    boolean updateCart(Cart cart);
}
