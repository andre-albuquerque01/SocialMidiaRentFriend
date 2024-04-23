package com.rentFriend.rentFriend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentFriend.rentFriend.dtos.CommentsDto;
import com.rentFriend.rentFriend.models.CommentsModel;
import com.rentFriend.rentFriend.services.CommentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentsService commentsService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getComment(@PathVariable UUID id){
		ResponseEntity<Object> response = this.commentsService.getComment(id);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> saveComments(@RequestBody @Valid CommentsDto data){
		ResponseEntity<Object> response = this.commentsService.saveComments(data);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> getComment(@PathVariable UUID id, @RequestBody @Valid CommentsModel data) throws Exception{
		ResponseEntity<Object> response = this.commentsService.updateComments(id, data);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
}
