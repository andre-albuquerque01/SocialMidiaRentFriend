package com.rentFriend.rentFriend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentFriend.rentFriend.models.CommentsModel;

public interface CommentsRepository extends JpaRepository<CommentsModel, UUID> {

	Optional<CommentsModel> findAllById(UUID id);

}
