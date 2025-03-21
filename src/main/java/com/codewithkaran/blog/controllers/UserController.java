package com.codewithkaran.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithkaran.blog.payloads.ApiResponse;
import com.codewithkaran.blog.payloads.UserDto;
import com.codewithkaran.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//PUT - update user
	@PutMapping("/{userId}")    // {userId} - path URI variable iski value get k liye @PathVariable use Karege 
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		//if in @PathVariable Integer userId  needs to change userId to uid then write @PathVariable("userId") Integer uid
		UserDto updatedUser = this.userService.updateUser(userDto,userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
		//OR ResponseEntity.ok(Map.of("message","User Deleted Successfully")); 
		// OR return new ResponseEntity<>(Map.of("message","User deleted successfully", HttpStatus.OK);
	}
	
	//GET - get all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//GET - get single user
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
			return ResponseEntity.ok(this.userService.getUserById(userId));
		}
}
