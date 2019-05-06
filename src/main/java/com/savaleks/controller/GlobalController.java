package com.savaleks.controller;

import com.savaleks.model.User;
import com.savaleks.model.UserModel;
import com.savaleks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Slf4j
@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    private UserModel userModel = null;

    @ModelAttribute
    public UserModel getUserModel() {
        if (httpSession.getAttribute("userModel") == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getByUsername(auth.getName());
            if (user != null) {
                userModel = new UserModel();
                userModel.setId(Math.toIntExact(user.getId()));
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());
                if (userModel.getRole().equals("USER")) {
                    userModel.setCart(user.getCart());
                }
            }
            httpSession.setAttribute("userModel", userModel);
            return userModel;
        }
        return (UserModel) httpSession.getAttribute("userModel");
    }
}
