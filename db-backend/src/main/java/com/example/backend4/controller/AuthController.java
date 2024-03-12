package com.example.backend4.controller;

import com.example.backend4.model.auth.RegisterMessage;
import com.example.backend4.model.auth.User;
import com.example.backend4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		if (authService.authenticateUser(user).isAuthenticated()){
			return new ResponseEntity<>(new RegisterMessage(authService.authenticateUser(user).getUser().getPassword()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RegisterMessage("Пользователь не найден"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<Object> register(@RequestBody User user) {
		if (!authService.registerUser(user)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RegisterMessage(authService.authenticateUser(user).getUser().getPassword()), HttpStatus.CREATED);
	}

}
