package com.rentFriend.rentFriend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentFriend.rentFriend.models.CommentsModel;

public interface CommentsRepository extends JpaRepository<CommentsModel, UUID> {

	@Query("SELECT c FROM CommentsModel c WHERE c.post.id = :postId")
	Optional<CommentsModel> findByPostId(@Param("postId") UUID postId);

}
