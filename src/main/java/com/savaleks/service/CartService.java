package com.savaleks.service;

import com.savaleks.model.Cart;
import com.savaleks.model.CartLine;
import com.savaleks.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
public class CartService {

    @Autowired
    private CartLineService cartLineService;

    @Autowired
    private HttpSession httpSession;

    // get the cart of the user who has logged in
    private Cart getCart(){
        return ((UserModel)httpSession.getAttribute("userModel")).getCart();
    }

    // fetch the data from database
    public List<CartLine> getCartLines(){
        Cart cart = this.getCart();
        return cartLineService.list(cart.getId());
    }
}
