package com.savaleks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter @ToString
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fullName;
    private String email;
    private String role;
    private Cart cart;
}
