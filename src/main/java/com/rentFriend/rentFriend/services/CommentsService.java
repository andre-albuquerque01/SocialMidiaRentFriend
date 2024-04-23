package com.rentFriend.rentFriend.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentFriend.rentFriend.dtos.CommentsDto;
import com.rentFriend.rentFriend.models.CommentsModel;
import com.rentFriend.rentFriend.models.PostModel;
import com.rentFriend.rentFriend.models.UserModel;
import com.rentFriend.rentFriend.repositories.CommentsRepository;
import com.rentFriend.rentFriend.repositories.PostRepository;
import com.rentFriend.rentFriend.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class CommentsService {

	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public ResponseEntity<Object> getComment(UUID id) {
		try {
			Optional<CommentsModel> _comments = this.commentsRepository.findAllById(id);

			if (_comments.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

			return ResponseEntity.status(HttpStatus.OK).body(_comments);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	public ResponseEntity<Object> saveComments(@RequestBody @Valid CommentsDto data) {
		try {
			UUID idUser = GetUserService.getIdUser();
			Optional<UserModel> _userOptional = userRepository.findById(idUser);
			Optional<PostModel> _postOptional = postRepository.findById(data.idPost());

			if (_userOptional.isEmpty() || _postOptional.isEmpty())
				return ResponseEntity.badRequest().body("User not found");

			UserModel _user = _userOptional.get();
			PostModel _post = _postOptional.get();
			LocalDateTime now = LocalDateTime.now();

			var _comments = new CommentsModel(_user, _post, data.comments(), now.toString(), now.toString());

			commentsRepository.save(_comments);

			return ResponseEntity.ok().body("Success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	public ResponseEntity<Object> updateComments(UUID id, CommentsModel data) throws Exception {
		try {
			Optional<CommentsModel> _comments = this.commentsRepository.findById(id);

			if (_comments.isEmpty())
				throw new Exception("Post não localizado");

			CommentsModel _Comments = _comments.get();
			LocalDateTime now = LocalDateTime.now();

			_Comments.setComments(data.getComments());
			_Comments.setUpdate_at(now.toString());
			
			this.commentsRepository.save(_Comments);
			
			return ResponseEntity.ok().body("Success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
