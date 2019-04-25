package com.savaleks.controller;

import com.savaleks.model.User;
import com.savaleks.model.UserModel;
import com.savaleks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    private UserModel userModel = null;

    @ModelAttribute("userModel")
    public UserModel getUserModel(Model model){
        if (httpSession.getAttribute("userModel") == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getByUsername(authentication.getName());
            if (user != null){
                userModel = new UserModel();
                userModel.setId(Math.toIntExact(user.getId()));
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());
                // set the cart if user is a buyer
                if (userModel.getRole().equals("USER")){
                    userModel.setCart(user.getCart());
                    model.addAttribute("cartLines", userModel.getCart().getCartLines());
                    model.addAttribute("grandTotal", userModel.getCart().getGrandTotal());
                }
                model.addAttribute("userFullName", userModel.getFullName());
                return userModel;
            }
        }
        return (UserModel) httpSession.getAttribute("userModel");
    }
}
