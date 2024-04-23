package com.rentFriend.rentFriend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentFriend.rentFriend.dtos.PostDto;
import com.rentFriend.rentFriend.models.PostModel;
import com.rentFriend.rentFriend.models.UserModel;
import com.rentFriend.rentFriend.repositories.PostRepository;
import com.rentFriend.rentFriend.repositories.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<Object> getOnePost(UUID id) {
		try {
			Optional<PostModel> _post = this.postRepository.findById(id);

			if (_post.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

			return ResponseEntity.status(HttpStatus.OK).body(_post);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> getuserPost() {
		try {
			UUID idUser = GetUserService.getIdUser();
			List<PostModel> _post = this.postRepository.findByUser_IdUser(idUser);

			if (_post.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

			return ResponseEntity.status(HttpStatus.OK).body(_post);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> getPostUser(UUID id) {
		try {
			List<PostModel> _post = this.postRepository.findByUser_IdUser(id);

			if (_post.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

			return ResponseEntity.status(HttpStatus.OK).body(_post);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> savePost(PostDto data) {
		try {
			UUID idUser = GetUserService.getIdUser();
			Optional<UserModel> _userOptional = userRepository.findById(idUser);

			if (_userOptional.isEmpty())
				return ResponseEntity.badRequest().body("User not found");

			UserModel _user = _userOptional.get();
			LocalDateTime now = LocalDateTime.now();

			var _post = new PostModel(data.imageOne(), data.imageTwo(), data.imageThree(), data.title(),
					data.description(), now.toString(), now.toString(), _user);

			postRepository.save(_post);

			return ResponseEntity.ok().body("Success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> updatePost(UUID id, PostModel data) throws Exception {
		try {
			Optional<PostModel> _post = this.postRepository.findById(id);

			if (_post.isEmpty())
				throw new Exception("Post não localizado");

			PostModel _Post = _post.get();
			LocalDateTime now = LocalDateTime.now();

			_Post.setImageOne(data.getImageOne());
			_Post.setImageTwo(data.getImageTwo());
			_Post.setImageThree(data.getImageThree());
			_Post.setTitle(data.getTitle());
			_Post.setDescription(data.getDescription());
			_Post.setUpdate_at(now.toString());

			return ResponseEntity.ok().body("Success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<Object> deleteOnePost(UUID id) {
		try {
			Optional<PostModel> _post = this.postRepository.findById(id);

			if (_post.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");

			this.postRepository.delete(_post.get());
			return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
