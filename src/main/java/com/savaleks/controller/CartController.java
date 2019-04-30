package com.savaleks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/show")
    private String showCart(Model model){
        model.addAttribute("cartItems", null);
        return "cart";
    }
}
