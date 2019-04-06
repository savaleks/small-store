package com.savaleks.handlers;


import com.savaleks.model.Address;
import com.savaleks.model.Cart;
import com.savaleks.model.User;
import com.savaleks.model.RegisterModel;
import com.savaleks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(RegisterHandler.class);

    @Autowired
    private UserService userService;

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model){
        String transitionValue = "success";

        // fetch the user
        User user = model.getUser();
        if (user.getRole().equals("USER")){
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        // save the user
        userService.addUser(user);

        // get the address
        Address billing = model.getBilling();
        billing.setUser(user);
        billing.setBilling(true);

        // save the address
        userService.addAddress(billing);

        return transitionValue;
    }

    public String validateUser(User user, MessageContext error) {
        String transitionValue = "success";

        // Checking the email for uniqueness
        if (userService.getByEmail(user.getEmail()) != null) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Email already used") //
                    .build());

            transitionValue = "failure";
        }

        // Checking if password matched the confirm password
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password doesn't match up the confirm password!") //
                    .build());

            transitionValue = "failure";
        }
        return transitionValue;
    }

}
