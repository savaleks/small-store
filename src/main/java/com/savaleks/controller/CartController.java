package com.savaleks.controller;

import com.savaleks.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                case "deleted":
                    model.addAttribute("message", "CartLine has been deleted!");
                    break;
                case "added":
                    model.addAttribute("message", "CartLine has been added!");
                    break;
                case "maximum":
                    model.addAttribute("message", "CartLine has reached maximum count!");
                    break;
                case "unavailable":
                    model.addAttribute("message", "Product quantity is not available!");
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
        String response = cartService.manageCartLine(cartLineId, count);

        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/{cartLineId}/delete")
    public String deleteCart(@PathVariable int cartLineId){
        String response = cartService.deleteCartLine(cartLineId);
        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/add/{productId}/product")
    public String addToCart(@PathVariable int productId){
        String response = cartService.addCartLine(productId);
        return "redirect:/cart/show?" + response;
    }
}
