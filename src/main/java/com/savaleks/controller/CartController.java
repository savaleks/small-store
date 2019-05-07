package com.savaleks.controller;

import com.savaleks.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/show")
    private String showCart(@RequestParam(name = "result", required = false)String result, Model model){
        if (result != null){
            switch (result){
                case "updated":
                    model.addAttribute("message", "CartLine has been updated!");
                    break;
                case "error":
                    model.addAttribute("message", "Something went wrong!");
                    break;
            }
        }
        model.addAttribute("cartLines", cartService.getCartLines());
        return "cart";
    }

    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count){
        String response = cartService.updateCartLine(cartLineId, count);

        return "redirect:/cart/show?" + response;
    }
}
