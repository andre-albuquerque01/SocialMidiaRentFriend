package com.rentFriend.rentFriend.dtos;

import com.rentFriend.rentFriend.models.enums.UserRole;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
		@NotBlank String password, UserRole role, @NotBlank String dateBirthday) {

}
