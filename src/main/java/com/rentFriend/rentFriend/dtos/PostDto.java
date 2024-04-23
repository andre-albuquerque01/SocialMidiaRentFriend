package com.rentFriend.rentFriend.dtos;

import com.rentFriend.rentFriend.models.UserModel;

import jakarta.validation.constraints.NotBlank;

public record PostDto(
		@NotBlank String imageOne, String imageTwo, String imageThree, @NotBlank String title, String description,
		String created_at, UserModel user) {

}
