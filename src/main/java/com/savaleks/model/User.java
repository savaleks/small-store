package com.savaleks.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String role;
    @Column(name = "is_enabled")
    private boolean enabled;
    @Column(name = "contact_number")
    private String contactNumber;
    private Date date = new Date();

}
