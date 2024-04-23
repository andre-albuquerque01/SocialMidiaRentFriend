package com.rentFriend.rentFriend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.rentFriend.rentFriend.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	UserDetails findByEmail(String email);
}
