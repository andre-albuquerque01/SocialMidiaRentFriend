package com.rentFriend.rentFriend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentFriend.rentFriend.models.PostModel;

public interface PostRepository extends JpaRepository<PostModel, UUID> {
	List<PostModel> findByUser_IdUser(UUID userId);
}
