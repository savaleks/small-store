package com.savaleks.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Address billing;
}
