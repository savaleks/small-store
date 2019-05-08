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
    private ProductService productService;

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
    public String manageCartLine(int cartLineId, int count) {
        CartLine cartLine = cartLineService.get(cartLineId);
        if (cartLine == null){
            return "result=error";
        } else {
            Product product = cartLine.getProduct();
            double oldTotal = cartLine.getTotal();

            // checking if the product is available
            if (product.getQuantity() < count){
                return "result=unavailable";
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

    public String addCartLine(int productId) {
        String response = null;
        Cart cart = this.getCart();
        CartLine cartLine = cartLineService.getByCartAndProduct(cart.getId(), productId);
        if (cartLine == null){
            // add new cartLine
            cartLine = new CartLine();
            // get the product
            Product product = productService.get(productId);
            cartLine.setCartId(cart.getId());
            cartLine.setProduct(product);
            cartLine.setBuyingPrice(product.getUnitPrice().doubleValue());
            cartLine.setProductCount(1);
            cartLine.setTotal(product.getUnitPrice().doubleValue());
            cartLine.setAvailable(true);
            cartLineService.add(cartLine);

            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            cartLineService.updateCart(cart);
            response = "result=added";

        } else {
            if (cartLine.getProductCount() < 3){
                response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
            } else {
                response = "result=maximum";
            }
        }
        return response;
    }
}
