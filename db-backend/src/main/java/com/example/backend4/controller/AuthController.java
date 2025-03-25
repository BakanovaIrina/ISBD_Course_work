package com.example.backend4.controller;

import com.example.backend4.model.auth.*;
import com.example.backend4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody UserRequest userRequest) {
		User user = new User(userRequest.getUsername(), userRequest.getPassword());
		if(authService.authenticateUser(user)){
			return new ResponseEntity<>(new RegisterMessage(user.getPassword()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RegisterMessage("Пользователь не найден"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<Object> register(@RequestBody UserRequestWithRole userRequestWithRole) {
		User user = new User(userRequestWithRole.getUsername(), userRequestWithRole.getPassword(),
				userRequestWithRole.getRole());
		if (!authService.registerUser(user)) {
			return new ResponseEntity<>(new RegisterMessage("Пользователь с таким именем уже существует"),
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RegisterMessage(user.getPassword()), HttpStatus.CREATED);
	}
}
