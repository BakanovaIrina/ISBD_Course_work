package com.example.backend4.services;

import com.example.backend4.model.auth.User;
import com.example.backend4.model.auth.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User parseAuthHeader(String authentication) {
        String[] credentials = authentication.replace("\"", "").split(":");

        return new User(credentials[0], credentials[1]);
    }

    public UserAuthentication authenticateUser(User user) {
        try {
            User foundByUsername = userService.loadUserByUsername(user.getUsername());
            boolean auth = passwordEncoder.matches(user.getPassword(), foundByUsername.getPassword());
            if(auth){
                User authUser = new User(user.getUsername(), user.getPassword());
                authUser.setId(foundByUsername.getId());
                return new UserAuthentication(authUser, true);
            }
            else {
                return new UserAuthentication(user, false);
            }
        } catch (UsernameNotFoundException e) {
            return new UserAuthentication(user, false);
        }
    }
/*
    public UserAuthentication findUser(User user) {
        try {
            User foundByUsername = userService.loadUser(user.getUsername(), user.getPassword());
            passwordEncoder.matches(user.getPassword(), foundByUsername.getPassword());
            return new UserAuthentication(foundByUsername, true);
        } catch (UsernameNotFoundException e) {
            return new UserAuthentication(user, false);
        }
    }

 */

    public boolean registerUser(User user) {
        try {
            userService.loadUserByUsername(user.getUsername());
            return false;
        } catch (UsernameNotFoundException e) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return true;
        }
    }

}
