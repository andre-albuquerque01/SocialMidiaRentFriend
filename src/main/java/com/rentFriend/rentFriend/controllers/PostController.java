package com.rentFriend.rentFriend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentFriend.rentFriend.dtos.PostDto;
import com.rentFriend.rentFriend.models.PostModel;
import com.rentFriend.rentFriend.repositories.PostRepository;
import com.rentFriend.rentFriend.services.GetUserService;
import com.rentFriend.rentFriend.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	PostService postService;

	@PostMapping("/register")
	public ResponseEntity<Object> savePost(@RequestBody @Valid PostDto data) {
	    ResponseEntity<Object> response = postService.savePost(data);
	    return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PostModel>> getAllPost() {
		return ResponseEntity.status(HttpStatus.OK).body(this.postRepository.findAll());
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Object> getOnePost(@PathVariable(name = "id") UUID id) {
		ResponseEntity<Object> response = postService.getOnePost(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
	@GetMapping("/getUserPosts")
	public ResponseEntity<Object> getuserPost() {
		ResponseEntity<Object> response = postService.getuserPost();
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
	@GetMapping("/gePostUser/{id}")
	public ResponseEntity<Object> getPostUser(@PathVariable(name = "id") UUID id) {
		ResponseEntity<Object> response = postService.getPostUser(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updatePost(@PathVariable(name = "id") UUID id, @RequestBody @Valid PostModel data) throws Exception {
		 ResponseEntity<Object> response = postService.updatePost(id, data);
		 return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Object> deleteOnePost(@PathVariable(name = "id") UUID id) {
		ResponseEntity<Object> response = postService.deleteOnePost(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
}
