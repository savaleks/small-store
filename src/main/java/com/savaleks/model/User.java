package com.savaleks.model;

import com.savaleks.model.security.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @ToString
public class User implements Serializable {

    @ToString.Exclude private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotBlank(message = "Please enter username")
    private String username;
    @NotBlank(message = "Please enter password")
    private String password;
    @NotBlank(message = "Please enter email")
    private String email;
    @NotBlank(message = "Please enter role")
    private String role;
    @Column(name = "is_enabled")
    private boolean enabled = true;
    @Column(name = "contact_number")
    private String contactNumber;
    private Date date = new Date();

    // confirm password transient field
    @Transient
    private String confirmPassword;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToMany
    private Set<Role> roles;

}
