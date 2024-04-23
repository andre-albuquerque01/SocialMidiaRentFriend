package com.rentFriend.rentFriend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentFriend.rentFriend.dtos.UserRecordDto;
import com.rentFriend.rentFriend.models.UserModel;
import com.rentFriend.rentFriend.repositories.UserRepository;
import com.rentFriend.rentFriend.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto data) {
		ResponseEntity<Object> response = this.userService.saveUser(data);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserModel>> getAllUser() {
		return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findAll());
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
		ResponseEntity<Object> response = this.userService.getOneUser(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid UserRecordDto data) {
		ResponseEntity<Object> response = this.userService.updateUser(id, data);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletUser(@PathVariable(value = "id") UUID id) {
		ResponseEntity<Object> response = this.userService.deletUser(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

}
