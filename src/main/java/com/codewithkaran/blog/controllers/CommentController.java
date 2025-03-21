package com.codewithkaran.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithkaran.blog.payloads.ApiResponse;
import com.codewithkaran.blog.payloads.CommentDto;
import com.codewithkaran.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
			//POST - create comment
			@PostMapping("/post/{postId}/comments")
			public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer postId){
				CommentDto createCommentDto = this.commentService.createComment(commentDto,postId);
				return new ResponseEntity<>(createCommentDto, HttpStatus.CREATED);
			}
			
			//delete - delete comment
			@DeleteMapping("/comments/{commentId}")
			public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
				this.commentService.deleteComment(commentId);
				return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true), HttpStatus.OK);
			}
}
