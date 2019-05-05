package com.savaleks.controller;

import com.savaleks.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/show")
    private String showCart(Model model){
        model.addAttribute("cartLines", cartService.getCartLines());
        return "cart";
    }
}
