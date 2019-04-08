package com.savaleks.service;

import com.savaleks.model.User;
import com.savaleks.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null){
            LOGGER.info("Email {} not found", email);
            throw new UsernameNotFoundException("Email " + email + " not found");
        }
        return (UserDetails) user;
    }
}
