package com.rentFriend.rentFriend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.rentFriend.rentFriend.models.enums.UserRole;
import com.rentFriend.rentFriend.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto data) {
		try {

			if (this.userRepository.findByEmail(data.email()) != null)
				return ResponseEntity.badRequest().build();
			var role = UserRole.USER;
			String encrypetPassword = new BCryptPasswordEncoder().encode(data.password());
			var userModel = new UserModel(data.firstName(), data.lastName(), data.email(), encrypetPassword, role,
					data.dateBirthday());
			this.userRepository.save(userModel);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserModel>> getAllUser() {
		return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findAll());
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
		Optional<UserModel> _user = userRepository.findById(id);
		if (_user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user");
		}
		return ResponseEntity.status(HttpStatus.OK).body(_user.get());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid UserRecordDto datas) {
		Optional<UserModel> _user = userRepository.findById(id);
		if (_user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		var userModel = _user.get();
		BeanUtils.copyProperties(datas, userModel);
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletUser(@PathVariable(value = "id") UUID id) {
		Optional<UserModel> _user = userRepository.findById(id);
		if (_user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user");
		}
		this.userRepository.delete(_user.get());
		return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
	}

}
