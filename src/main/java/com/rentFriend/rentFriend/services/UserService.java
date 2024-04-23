package com.rentFriend.rentFriend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
