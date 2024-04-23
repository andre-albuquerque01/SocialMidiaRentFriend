package com.rentFriend.rentFriend.services;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rentFriend.rentFriend.models.UserModel;

public class GetUserService {
	
	public static UUID getIdUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		UserModel customUser = (UserModel) principal;
		UUID userId = customUser.getIdUser();
		
		return userId;
	}
}
