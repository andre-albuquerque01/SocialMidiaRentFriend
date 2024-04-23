package com.rentFriend.rentFriend.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentFriend.rentFriend.dtos.UserRecordDto;
import com.rentFriend.rentFriend.models.UserModel;
import com.rentFriend.rentFriend.models.enums.UserRole;
import com.rentFriend.rentFriend.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<Object> saveUser(UserRecordDto data) {
		try {
			if (this.userRepository.findByEmail(data.email()) != null)
				return ResponseEntity.badRequest().build();

			var role = UserRole.USER;
			String encrypetPassword = new BCryptPasswordEncoder().encode(data.password());
			var userModel = new UserModel(data.firstName(), data.lastName(), data.email(), encrypetPassword, role,
					data.dateBirthday());

			this.userRepository.save(userModel);

			return ResponseEntity.ok().body("Sucess");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> getOneUser(UUID id) {
		try {
			Optional<UserModel> _user = userRepository.findById(id);
			if (_user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user");
			}
			return ResponseEntity.status(HttpStatus.OK).body(_user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> updateUser(UUID id, UserRecordDto datas) {
		try {
			Optional<UserModel> _user = userRepository.findById(id);
			if (_user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
			var userModel = _user.get();
			BeanUtils.copyProperties(datas, userModel);
			this.userRepository.save(userModel);
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> deletUser(UUID id) {
		try {
			Optional<UserModel> _user = userRepository.findById(id);
			if (_user.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user");
			}
			this.userRepository.delete(_user.get());
			return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
