package com.rentFriend.rentFriend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentFriend.rentFriend.dtos.AuthenticationDto;
import com.rentFriend.rentFriend.dtos.LoginDto;
import com.rentFriend.rentFriend.infra.security.TokenService;
import com.rentFriend.rentFriend.models.UserModel;
import com.rentFriend.rentFriend.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data) {
		ResponseEntity<Object> response = this.userService.login(data);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
}
