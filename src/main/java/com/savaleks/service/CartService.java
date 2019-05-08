package com.savaleks.service;

import com.savaleks.model.Cart;
import com.savaleks.model.CartLine;
import com.savaleks.model.Product;
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

    // updating cartLine -> count and price
    public String updateCartLine(int cartLineId, int count) {
        CartLine cartLine = cartLineService.get(cartLineId);
        if (cartLine == null){
            return "result=error";
        } else {
            Product product = cartLine.getProduct();
            double oldTotal = cartLine.getTotal();
            if (product.getQuantity() <= count){
                count = product.getQuantity();
            }
            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice().doubleValue());
            cartLine.setTotal(product.getUnitPrice().doubleValue() * count);
            cartLineService.update(cartLine);
            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
            cartLineService.updateCart(cart);

            return "result=updated";
        }
    }

    public String deleteCartLine(int cartLineId) {
        CartLine cartLine = cartLineService.get(cartLineId);
        if (cartLine == null){
            return "result=error";
        } else {
            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() - 1);
            cartLineService.updateCart(cart);

            // remove cartLine
            cartLineService.delete(cartLine);
            return "result=deleted";
        }
    }
}
