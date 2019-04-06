package com.savaleks.handlers;


import com.savaleks.model.Address;
import com.savaleks.model.User;
import com.savaleks.model.RegisterModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(RegisterHandler.class);

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

}
