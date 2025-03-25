package com.example.backend4.services;

import com.example.backend4.model.auth.User;
import com.example.backend4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User parseAuthHeader(String authentication) {
        String[] credentials = authentication.replace("\"", "").split(":");

        return new User(credentials[0], credentials[1]);
    }

    public boolean authenticateUser(User user) {
        try {
            User foundByUsername = userRepository.findByUsername(user.getUsername());
            if (foundByUsername == null) {
                return false;
            }
            return passwordEncoder.matches(user.getPassword(), foundByUsername.getPassword());
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public boolean registerUser(User user) {

        User u = userRepository.findByUsername(user.getUsername());
        if(u == null){
            userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getRole()));
            return true;
        }
        else {
            return false;
        }
    }

}
